package com.example.mobileapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.customer.CustomerRegisterActivity;
import com.example.mobileapplication.activities.customer.LoginActivity;
//import com.example.mobileapplication.activities.customer.PaymentActivity;

public class MainActivity extends AppCompatActivity {
    private Button getStartbtn, testBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStartbtn = findViewById(R.id.getStartbtn);
        testBtn = findViewById(R.id.button);

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
                Intent intent = new Intent(MainActivity.this, CustomerRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}