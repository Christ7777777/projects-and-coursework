package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.API.MockApiClient;
import com.example.drivewayparking.Model.DisputeRequest;
import com.example.drivewayparking.Model.DisputeResponse;
import com.example.drivewayparking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDisputeActivity extends AppCompatActivity {

    private long bid;
    private String message;

    private EditText Message;
    private Button Create;

    private DisputeRequest dispute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dispute);

        Message = findViewById(R.id.disputeMessage);
        Create = findViewById(R.id.create);

        bid = getIntent().getExtras().getLong("bid");

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = Message.getText().toString().trim();

                if (message == null || message.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter a message", Toast.LENGTH_SHORT).show();
                    return;
                }

                dispute = new DisputeRequest();
                dispute.setMessage(message);
                dispute.setBookingId(bid);

                Call<Integer> call = ApiClient.getDisputeApiService().addDispute(dispute);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Dispute Created Successfully " + response.body(), Toast.LENGTH_LONG).show();
                            //finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Dispute Creation Failed  " + response.code(), Toast.LENGTH_LONG).show();
                            System.out.println(dispute.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Dispute not Created Successfully: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(dispute.toString());
                    }
                });
            }
        });
    }
}