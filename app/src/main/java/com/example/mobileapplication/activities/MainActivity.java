package com.example.mobileapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.customer.LoginActivity;
import com.example.mobileapplication.activities.customer.PaymentActivity;

public class MainActivity extends AppCompatActivity {
    public Button getStartbtn, testBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBtn = findViewById(R.id.TestButton);
        getStartbtn = findViewById(R.id.getStartbtn);
        getStartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });


    }
}