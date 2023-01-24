package com.example.postrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        StudentAPI studentAPI = retrofit.create(StudentAPI.class);
        Call<List<Student>> call = studentAPI.getStudents();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {

                if(!response.isSuccessful()){
                    textView.setText(response.code());
                    return;
                }
                List<Student> students = response.body();
                for(Student student : students){
                    String str = "";
                    str += "ID: " + student.getId() + "\n";
                    str += "First Name: " + student.getFirstName() + "\n";
                    str += "Last Name: " + student.getLastName() + "\n";
                    str += "Age: " + student.getAge() + "\n";
                    str += "Email: " + student.getEmail() + "\n\n";

                    textView.append(str);

                }

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
             textView.setText(t.getMessage());
            }
        });
    }
}