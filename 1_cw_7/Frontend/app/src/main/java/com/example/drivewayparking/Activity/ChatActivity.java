package com.example.drivewayparking.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    Button b1, b2;
    EditText e1, e2;
    TextView t1;
    String email;
    Long userId;

    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        e1 = findViewById(R.id.et1);
        e2 = findViewById(R.id.et2);
        t1 = findViewById(R.id.tx1);
        email = getIntent().getExtras().getString("f_email");
        Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    userId = response.body().getId();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Draft[] drafts = {
                        new Draft_6455()
                };

                /**
                 * If running this on an android device, make sure it is on the same network as your
                 * computer, and change the ip address to that of your computer.
                 * If running on the emulator, you can use localhost.
                 */
                String w = "ws://coms-309-015.class.las.iastate.edu:8080/message/" + e1.getText().toString();

                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w), drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            Log.d("", "run() returned: " + message);
                            String s = t1.getText().toString();
                            t1.setText(s + "\nServer:" + message);
                        }

                        @Override
                        public void onOpen(ServerHandshake handshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned: " + reason);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Exception:", e.toString());
                        }
                    };
                } catch (URISyntaxException e) {
                    Log.d("Exception:", e.getMessage());
                    e.printStackTrace();
                }
                cc.connect();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(e2.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage());
                }
            }
        });
    }
}
