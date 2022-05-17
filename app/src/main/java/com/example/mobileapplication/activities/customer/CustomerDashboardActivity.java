package com.example.mobileapplication.activities.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapplication.R;

public class CustomerDashboardActivity extends AppCompatActivity {

    private Button groceryBtn, pharmacyBtn, bakeryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        groceryBtn = findViewById(R.id.Grocery_Item);

        groceryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerDashboardActivity.this, CustomerGroceryMainActivity.class);
                startActivity(intent);

            }
        });
    }
}