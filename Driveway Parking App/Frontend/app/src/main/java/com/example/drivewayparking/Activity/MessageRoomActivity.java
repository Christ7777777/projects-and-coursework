package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * The type Message list activity.
 * @author: Varun Advani
 */
public class MessageRoomActivity extends AppCompatActivity {

    TextView receiver_email;
    Button create_room;
    private String r_email, s_email;
    private Long receiver_id;
    private Long sender_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        receiver_email = findViewById(R.id.editText_receiver_email);
        create_room = findViewById(R.id.enterBtn);


        Bundle bundle = new Bundle();
        bundle.putString("sender_email", getIntent().getExtras().getString("f_email"));
        s_email = getIntent().getExtras().getString("f_email");
        Call<User> call_sender = ApiClient.getUserApiService().getUserByEmail(s_email);
        call_sender.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    sender_id = response.body().getId();
                    bundle.putLong("sender_id", response.body().getId());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code S : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r_email = receiver_email.getText().toString();
                System.out.println("Receiver Email: " + r_email);
                Call<User> call_receiver = ApiClient.getUserApiService().getUserByEmail(r_email);
                call_receiver.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            receiver_id = response.body().getId();
                            bundle.putLong("receiver_id", response.body().getId());
                            bundle.putString("r_email", r_email);
                            System.out.println("Receiver ID: " + receiver_id);
                            Intent intent = new Intent(MessageRoomActivity.this, MessageActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else{
                             Toast.makeText(getApplicationContext(), "Code R : " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//                Intent intent = new Intent(MessageRoomActivity.this, MessageActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
//        bundle.putLong("receiver_id", receiver_id);
//        bundle.putLong("sender_id", sender_id);
//        bundle.putString("sender_email", s_email);
    }
}
