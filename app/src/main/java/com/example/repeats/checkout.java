package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class checkout extends AppCompatActivity {
    private Button datePickerButton;
    Button paynow;

    private int year, month, day;
    CheckBox terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        terms=findViewById(R.id.tandc);
        paynow=findViewById(R.id.paynow);
        datePickerButton = findViewById(R.id.datePickerButton);
        String startDate = datePickerButton.getText().toString();
        String userName = getIntent().getStringExtra("userName");

        datePickerButton.setOnClickListener(v -> {

            if (year == 0) {
                // Use the current date as the default
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
            }


            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    checkout.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {

                        year = selectedYear;
                        month = selectedMonth;
                        day = selectedDay;

                        datePickerButton.setText(day + "/" + (month + 1) + "/" + year);
                    },
                    year, month, day
            );


            datePickerDialog.show();
        });
        paynow.setOnClickListener(view -> {
            if (!terms.isChecked()) {

                Toast.makeText(this, "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
            } else {

                Intent i = new Intent(checkout.this, dashboard.class);


                // Pass the name to the dashboard activity
                i.putExtra("userName", userName);

                startActivity(i);

            }
        });
    }
}