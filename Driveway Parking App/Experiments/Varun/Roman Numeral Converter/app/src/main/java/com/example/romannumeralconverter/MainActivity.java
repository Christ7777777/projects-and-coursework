package com.example.romannumeralconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button convertToDecimal, convertToRoman;
    EditText inputDecimal, inputRoman;
    TextView textViewDecimal, textViewRoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertToDecimal = (Button) findViewById(R.id.convertToDecimal);
        convertToRoman = (Button) findViewById(R.id.convertToRoman);
        inputDecimal = (EditText) findViewById(R.id.inputDecimal);
        inputRoman = (EditText)  findViewById(R.id.inputRoman);
        textViewDecimal = (TextView) findViewById(R.id.textViewDecimal);
        textViewRoman = (TextView) findViewById(R.id.textViewRoman);

        convertToDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String romanNumeral = "";

                romanNumeral = inputRoman.getText().toString();

                FormatConverter formatConverter = new FormatConverter();

                int result = formatConverter.toDecimal(romanNumeral);
                System.out.println(result);
                textViewDecimal.setText(Integer.toString(result));
                // textViewDecimal.setText(result);
                //textViewRoman.setText(Integer.toString(result));
            }
        });

        convertToRoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number;
                String roman;
                number = Integer.parseInt(inputDecimal.getText().toString());

                FormatConverter formatConverter = new FormatConverter();

                roman = formatConverter.toRoman(number);
                System.out.println(roman);
                textViewRoman.setText((roman));
            }
        });

    }
}