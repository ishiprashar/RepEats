package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class mealpref extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealpref);
        Button nutridec=findViewById(R.id.nutridec);
        Button keto=findViewById(R.id.keto);
        Button lowcarb=findViewById(R.id.lowcarb);
        Button vegann=findViewById(R.id.vegan);
        Button balanc=findViewById(R.id.bal);

        ImageButton back=findViewById(R.id.backk);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mealpref.this, personalinfo.class);
                startActivity(i);
            }
        });
        nutridec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mealpref.this, foodpref.class);
                startActivity(i);
            }
        });
        keto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mealpref.this, foodpref.class);
                startActivity(i);
            }
        });
        vegann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mealpref.this, planinfo.class);
                startActivity(i);
            }
        });
        balanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mealpref.this, foodpref.class);
                startActivity(i);
            }
        });
        lowcarb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(mealpref.this, foodpref.class);
                startActivity(i);
            }
        });




    }
}