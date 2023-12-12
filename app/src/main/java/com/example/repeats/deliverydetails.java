package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class deliverydetails extends AppCompatActivity {
    Button next;
    ImageButton back;
    EditText addLine1, addLine2, pincode, city, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliverydetails);

        addLine1 = findViewById(R.id.addline1);
        addLine2 = findViewById(R.id.addline2);
        pincode = findViewById(R.id.pincode);
        city = findViewById(R.id.City);
        state = findViewById(R.id.State);


        next = findViewById(R.id.nextdel);
        back = findViewById(R.id.back);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    // Input is valid, proceed to the next activity (checkout)
                    Intent i = new Intent(deliverydetails.this, checkout.class);


                    String userName = getIntent().getStringExtra("userName");

                    // Pass the name to the checkout activity
                    i.putExtra("userName", userName);
                    startActivity(i);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(deliverydetails.this, basicinfo.class);
                startActivity(i);
            }
        });
    }

    private boolean validateInput() {
        String addressLine1 = addLine1.getText().toString().trim();
        String addressLine2 = addLine2.getText().toString().trim();
        String pincodeText = pincode.getText().toString().trim();
        String cityText = city.getText().toString().trim();
        String stateText = state.getText().toString().trim();

        if (addressLine1.isEmpty() || addressLine2.isEmpty() || pincodeText.isEmpty() || cityText.isEmpty() || stateText.isEmpty()) {
            // Display an error message if any field is empty
            setErrorForEmptyField(addLine1, "Address Line 1 is required");
            setErrorForEmptyField(addLine2, "Address Line 2 is required");
            setErrorForEmptyField(pincode, "Pincode is required");
            setErrorForEmptyField(city, "City is required");
            setErrorForEmptyField(state, "State is required");
            return false;
        }

        return true;
    }

    private void setErrorForEmptyField(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
    }
}
