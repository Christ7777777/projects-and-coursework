package com.example.drivewayparking.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.MessageAdapter;
import com.example.drivewayparking.Model.MessageRequest;
import com.example.drivewayparking.Model.MessageResponse;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import retrofit2.Call;
import retrofit2.Callback;

public class MessageActivity extends AppCompatActivity implements TextWatcher {

    private String email, username, date, time, receiver_email, receiver_name;
    private Long receiver_id, sender_id;
    private WebSocket webSocket;
    private String SERVER_PATH = "";
    private EditText messageEdit;
    private Button sendBtn;
    private ImageView pickImgBtn;
    private RecyclerView recyclerView;
    private final int IMAGE_REQUEST_ID = 1;
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        email = getIntent().getExtras().getString("sender_email");
        receiver_email = getIntent().getExtras().getString("r_email");
        System.out.println("Receiver email: " + receiver_email);
        sender_id = getIntent().getExtras().getLong("sender_id");
        receiver_id = getIntent().getExtras().getLong("receiver_id");
        recyclerView = findViewById(R.id.recyclerView);
        messageAdapter = new MessageAdapter(getLayoutInflater(), date, time);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println(email);
        System.out.println(sender_id);
        System.out.println(receiver_id);
        initiateSocketConnection();

    }

    private void initiateSocketConnection() {

        OkHttpClient client = new OkHttpClient();
        SERVER_PATH = "ws://coms-309-015.class.las.iastate.edu:8080/message/" + sender_id;
        Request request = new Request.Builder().url(SERVER_PATH).build();
        webSocket = client.newWebSocket(request, new SocketListener());

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String string = s.toString().trim();

        if (string.isEmpty()) {
            resetMessageEdit();
        } else {

            sendBtn.setVisibility(View.VISIBLE);
            pickImgBtn.setVisibility(View.INVISIBLE);
        }

    }

    private void resetMessageEdit() {

        messageEdit.removeTextChangedListener(this);

        messageEdit.setText("");
        sendBtn.setVisibility(View.INVISIBLE);
        pickImgBtn.setVisibility(View.VISIBLE);

        messageEdit.addTextChangedListener(this);

    }

    private class SocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);

            runOnUiThread(() -> {
                Toast.makeText(MessageActivity.this,
                        "Socket Connection Successful!",
                        Toast.LENGTH_SHORT).show();


                initializeView();
            });
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);

            runOnUiThread(() -> {
                MessageResponse message = new MessageResponse();
                if(text != null) {
                    String[] data = text.split("\n", 4);
                    if(data.length == 4) {
                    message.setMessage(data[3]);
                    message.setOtherId(receiver_id);
                    message.setSent(Boolean.parseBoolean(data[2]));
                    message.setName(data[0]);
                    message.setSent(Timestamp.valueOf(data[1]));
                    Call<User> call = ApiClient.getUserApiService().getUserByEmail(receiver_email);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                            if (response.isSuccessful()) {
                                String name = response.body().getFirstName() + " " + response.body().getLastName();
                                System.out.println("Receiver Name; " + name);
                                if (name.equals(data[0])) {
                                    messageAdapter.addItem(message);
                                    messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
                                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                                    //messageAdapter.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
                }
            });

        }
    }

    private void initializeView() {

        messageEdit = findViewById(R.id.messageEdit);
        sendBtn = findViewById(R.id.sendBtn);
        pickImgBtn = findViewById(R.id.pickImgBtn);
        //recyclerView = findViewById(R.id.recyclerView);
        messageAdapter = new MessageAdapter(getLayoutInflater(), date, time);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Timestamp timestamp = new Timestamp(date.getTime());


        messageEdit.addTextChangedListener(this);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                Timestamp timestamp = new Timestamp(date.getTime());
                MessageResponse message = new MessageResponse();
                message.setMessage(messageEdit.getText().toString());

                message.setName(username);
                message.setSent(true);

                    webSocket.send(receiver_id + "\n" + messageEdit.getText().toString());

                    messageAdapter.addItem(message);
                    messageAdapter.notifyDataSetChanged();

                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

                    resetMessageEdit();

            }
        });

        pickImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageAdapter.getItemCount() > 0){
                    messageAdapter.notifyDataSetChanged();
                }
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//
//                startActivityForResult(Intent.createChooser(intent, "Pick image"),
//                        IMAGE_REQUEST_ID);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                sendImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    private void sendImage(Bitmap image) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);

        String base64String = Base64.encodeToString(outputStream.toByteArray(),
                Base64.DEFAULT);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", username);
            jsonObject.put("image", base64String);

            webSocket.send(jsonObject.toString());

            jsonObject.put("isSent", true);
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}