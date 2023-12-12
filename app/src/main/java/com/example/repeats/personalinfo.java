package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class personalinfo extends AppCompatActivity {
    private EditText feet,inches,weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo);
        Button next= findViewById(R.id.next2);
        ImageButton backButton = findViewById(R.id.back);
        feet=findViewById(R.id.heighted);
        inches=findViewById(R.id.heighted2);
        weight=findViewById(R.id.weighted);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }

    });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feetText = feet.getText().toString().trim();
                String inchesText = inches.getText().toString().trim();
                String weightText = weight.getText().toString().trim();

                if (feetText.isEmpty() || inchesText.isEmpty() || weightText.isEmpty()) {
                    Toast.makeText(personalinfo.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        double feetValue = Double.parseDouble(feetText);
                        double inchesValue = Double.parseDouble(inchesText);
                        double weightValue = Double.parseDouble(weightText);


                        double totalHeightInInches = (feetValue * 12.0) + inchesValue;


                        if (totalHeightInInches >= 36.0 && totalHeightInInches <= 96.0 &&
                                weightValue >= 25.0 && weightValue <= 500.0) {

                            Intent i = new Intent(personalinfo.this, mealpref.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(personalinfo.this, "Invalid height or weight values", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(personalinfo.this, "Invalid numeric format", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



}}