package com.example.mobileapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.bakery.Bakery_Manager_login;
import com.example.mobileapplication.activities.grocery.GManagerLogin;
import com.example.mobileapplication.activities.pharmacy.PHManagerLogin;

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


                Intent intent = new Intent(ManagerLoginActivity.this, GManagerLogin.class);
                startActivity(intent);
                finish();

            }
        });

        PharmacyManBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerLoginActivity.this, PHManagerLogin.class);
                startActivity(intent);
                finish();
            }
        });

        BakeryManBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerLoginActivity.this, Bakery_Manager_login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}