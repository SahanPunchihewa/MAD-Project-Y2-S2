package com.example.mobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerLoginActivity extends AppCompatActivity {

    Button GroceryManBtn,PharmacyManBtn,BakeryManBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        GroceryManBtn=findViewById(R.id.Grocery_manager_login);
        PharmacyManBtn=findViewById(R.id.Pharmacy_manager_login);
        BakeryManBtn=findViewById(R.id.Bakery_manager_login);

        GroceryManBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManagerLoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        PharmacyManBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerLoginActivity.this, PHManagerLogin.class);
                startActivity(intent);
            }
        });

        BakeryManBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}