package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class foodpref extends AppCompatActivity {
    Button vegg,nonvegg,eggg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodpref);
        vegg=findViewById(R.id.veg);
        nonvegg= findViewById(R.id.nonveg);
        eggg=findViewById(R.id.egg);
        vegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(foodpref.this, planinfo.class);
                startActivity(i);
            }
        });
        nonvegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(foodpref.this, planinfo.class);
                startActivity(i);
            }
        });
        eggg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(foodpref.this, planinfo.class);
                startActivity(i);
            }
        });






    }
}