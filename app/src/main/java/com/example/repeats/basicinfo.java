package com.example.repeats;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class basicinfo extends AppCompatActivity {
    EditText namee, emaill, passwordd;
   FirebaseAuth mAuth;
   FirebaseFirestore fstore;
    FirebaseAuth fAuth;

    Button next;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinfo);
        namee = findViewById(R.id.name);
        emaill = findViewById(R.id.email);
        passwordd = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        fAuth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        next = findViewById(R.id.nextbasic);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = null;
                String password = null;
                if (validateInput()) {
                    String name = namee.getText().toString().trim();
                    email = emaill.getText().toString().trim();
                    password = passwordd.getText().toString();


                    // Password validation
                    if (!isValidPassword(password)) {
                        passwordd.setError("Invalid password. It must be at most 8 characters long, contain at least one uppercase letter, and one number.");
                        return;
                    }


                }
                String finalEmail = email;
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(basicinfo.this, "Account Created!",
                                            Toast.LENGTH_SHORT).show();
                                    String name = namee.getText().toString().trim();
                                    userID=fAuth.getCurrentUser().getUid();

                                    DocumentReference documentReference=fstore.collection("users").document(userID);
                                    Map<String,Object> user=new HashMap<>();
                                    user.put("fName",name);
                                    user.put("email", finalEmail);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid){
                                            Log.d(TAG,"onSuccess: user Profile is created for "+userID);


                                        }
                                    });


                                    Intent i= new Intent(getApplicationContext(),deliverydetails.class);



                                  //  i.putExtra("userName", userName);
                                    startActivity(i);
                                    finish();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(basicinfo.this, "Account creation failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


    }

    // Validation method for empty fields, email syntax, and password
    private boolean validateInput() {
        String name = namee.getText().toString().trim();
        String email = emaill.getText().toString().trim();
        String password = passwordd.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(name)) {
                namee.setError("Please fill in your name");
            }
            if (TextUtils.isEmpty(email)) {
                emaill.setError("Please fill in your email");
            }
            if (TextUtils.isEmpty(password)) {
                passwordd.setError("Please fill in your password");
            }
            return false;
        }

        if (!isValidEmail(email)) {
            emaill.setError("Invalid email address");
            return false;
        }

        return true;
    }



    // Email validation method
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Password validation method
    private boolean isValidPassword(String password) {
        if (password.length() > 8) {
            return false;
        }
        // Use regular expressions to check for uppercase letter and number
        String pattern = "^(?=.*[A-Z])(?=.*\\d).+$";
        return Pattern.compile(pattern).matcher(password).matches();
    }});}}
