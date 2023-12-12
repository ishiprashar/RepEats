package com.example.repeats;

import static android.opengl.Matrix.length;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SignupLoginActivity extends AppCompatActivity {

    private Button signUpButton;
    private Button loginButton;

    private Button newuser;
    private Button existinguser;
    private ViewPager viewPager;
    private ImagePagerAdapter adapter;
    private ArrayList<Integer> images;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 1000; // Delay in milliseconds
    private final long PERIOD_MS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        // Initializing views
        newuser = findViewById(R.id.newu);
        existinguser= findViewById(R.id.existing);
        viewPager = findViewById(R.id.viewPager);
        images = new ArrayList<>();

        // Adding image resource IDs to the 'images' ArrayList
        images.add(R.drawable.nutri);
        images.add(R.drawable.meal);
        images.add(R.drawable.goal);
        images.add(R.drawable.doorstep);
        adapter = new ImagePagerAdapter(this, images);
        viewPager.setAdapter(adapter);

        // Seting up a timer that  change images automatically
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == images.size()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(update);
            }
        }, DELAY_MS, PERIOD_MS);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SignupLoginActivity.this, newdetails.class);
                startActivity(i);

            }
        });
        existinguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SignupLoginActivity.this,existinguser.class);
                startActivity(i);
            }
        });

    }
    }







