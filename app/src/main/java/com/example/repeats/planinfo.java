package com.example.repeats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class planinfo extends AppCompatActivity {
    Button next;
    RadioButton day1, day14, day30;
    CheckBox breakk, lunchh, dinner;
    TextView priceCalc, subtotalDisplay, gstDisplay,overalltotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planinfo);

        day1 = findViewById(R.id.day1);
        day14 = findViewById(R.id.day14);
        day30 = findViewById(R.id.day30);
        breakk = findViewById(R.id.breakf);
        lunchh = findViewById(R.id.lunch);
        overalltotal=findViewById(R.id.overalltotal);
        dinner = findViewById(R.id.dinner);
        next = findViewById(R.id.nextplan);
        priceCalc = findViewById(R.id.pricecalc);
        subtotalDisplay = findViewById(R.id.subtotaldisplay);
        gstDisplay = findViewById(R.id.gstdisplay);



        day1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateAndDisplayPrice();
            }
        });

        day14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateAndDisplayPrice();
            }
        });

        day30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateAndDisplayPrice();
            }
        });

        breakk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateAndDisplayPrice();
            }
        });

        lunchh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateAndDisplayPrice();
            }
        });

        dinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateAndDisplayPrice();
            }
        });

        calculateAndDisplayPrice();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(planinfo.this, basicinfo.class);
                startActivity(i);
            }
        });
    }

    private void calculateAndDisplayPrice() {
        int basePrice = 100;
        int days = 0;

        if (day1.isChecked()) {
            days = 1;
        } else if (day14.isChecked()) {
            days = 14;
        } else if (day30.isChecked()) {
            days = 30;
        }
        int breakfastPrice = breakk.isChecked() ? 500 : 0;
        int lunchPrice = lunchh.isChecked() ? 500 : 0;
        int dinnerPrice = dinner.isChecked() ? 500 : 0;
        int totalMealPrice = (breakfastPrice + lunchPrice + dinnerPrice) * days;
        int subtotal = basePrice * days + totalMealPrice;

        double gst = calculateGST(subtotal);
        double deliveryCharge = 500;

        // Calculate overall total including GST and delivery charge
        double overallTotal = subtotal + gst + deliveryCharge;

        priceCalc.setText("Rs. " + subtotal + " for " + days + " days");
        subtotalDisplay.setText("Rs. " + subtotal);
        gstDisplay.setText("Rs. " + gst);
        overalltotal.setText("Rs. " + overallTotal);

    }

    private double calculateGST(double totalPrice) {
        double gstRate = 0.18;
        double gstAmount = totalPrice * gstRate;
        return gstAmount;
    }
}
