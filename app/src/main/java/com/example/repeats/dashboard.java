package com.example.repeats;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.time.LocalTime;

public class dashboard extends AppCompatActivity {
    TextView namee,namedisp;
    Button custsup,logout,driverr;
    private static final int REQUEST_PHONE_CALL = 1;
    TextView greetingTextView,email;
    FirebaseUser user;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        logout=findViewById(R.id.logout);
        driverr=findViewById(R.id.driversup);
        email=findViewById(R.id.email1);
         namedisp=findViewById(R.id.namesec);
        namee=findViewById(R.id.name);
        fAuth=FirebaseAuth.getInstance();

        fstore = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userId=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference= fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot , @Nullable FirebaseFirestoreException error) {
                namee.setText(documentSnapshot.getString("fName"));
                namedisp.setText(documentSnapshot.getString("fName"));
            }
        });
        driverr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        user=auth.getCurrentUser();
        if(user==null)
        {
            Intent i=new Intent(getApplicationContext(),existinguser.class);
            startActivity(i);
            finish();
        }
        else{
            email.setText(user.getEmail());

        }

        LocalTime currentTime = null;
        greetingTextView = findViewById(R.id.greeting);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalTime.now();
        }
        int hour = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hour = currentTime.getHour();
        }
        String greetingMessage;
        if (hour >= 6 && hour < 12) {
            greetingMessage = "Good morning";
        } else if (hour >= 12 && hour < 17) {
            greetingMessage = "Good afternoon";
        } else {
            greetingMessage = "Good night";
        }




        custsup = findViewById(R.id.custsup);

        custsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "8652615196"; // Replace with the actual phone number

                if (ContextCompat.checkSelfPermission(dashboard.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(dashboard.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    makePhoneCall(phoneNumber);
                }
            }
        }); // Correctly aligned closing brace


        greetingTextView.setText(greetingMessage);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                fAuth.signOut();

                Intent i=new Intent(getApplicationContext(),SignupLoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }



    private void makePhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);


        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String phoneNumber = "8652615196"; // Replace with the actual phone number
                makePhoneCall(phoneNumber);
            } else {
                // Handle permission denied here.
            }
        }
    }
}

//