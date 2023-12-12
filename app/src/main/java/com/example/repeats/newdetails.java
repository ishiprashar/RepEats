package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


    public class newdetails extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_newdetails);
            ImageButton backButton = findViewById(R.id.back);
            Button losew= findViewById(R.id.lw) ;
            Button gainm=findViewById(R.id.gm);
            Button weightm=findViewById(R.id.wm);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i= new Intent(newdetails.this, SignupLoginActivity.class);
                    startActivity(i);
                }

            });
            losew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  String selectedGoal = "Lose Weight"; // Set the selected goal
                    Intent i = new Intent(newdetails.this, personalinfo.class);
                //    i.putExtra(selectedGoal,"Lose Weight");
                    startActivity(i);


                }
            });
            gainm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i= new Intent(newdetails.this, personalinfo.class);
                    startActivity(i);

                }
            });
            weightm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i= new Intent(newdetails.this, personalinfo.class);
                    startActivity(i);

                }
            });

        }
    }



