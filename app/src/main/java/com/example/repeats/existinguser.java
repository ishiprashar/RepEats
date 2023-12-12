package com.example.repeats;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class existinguser extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    FirebaseAuth mAuth;
    private Button next;
    private TextView userbackk;

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(), dashboard.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existinguserr);
        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.email);
        userbackk = findViewById(R.id.userback);
        passwordEditText = findViewById(R.id.pass);
        next = findViewById(R.id.next1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEditText.setError("Enter an email");
                } else {
                    emailEditText.setError(null); // Clear any previous error
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Enter a password");
                } else {
                    passwordEditText.setError(null); // Clear any previous error
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(existinguser.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), dashboard.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    // If sign-in fails, display a message to the user.
                                    Toast.makeText(existinguser.this, "Login failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void usersignup(View view) {
        Intent i = new Intent(existinguser.this, SignupLoginActivity.class);
        startActivity(i);
    }
}
