package com.example.mobileapplication.activities.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.ManagerLoginActivity;

public class LoginActivity extends AppCompatActivity {

    private Button cusLogin, managerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        managerLogin = findViewById(R.id.Login_Manager_btn);
       // cusLogin =findViewById(R.id.LoginCustomerBtn);


       managerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ManagerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    /*    cusLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CustomerRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }); */
    }
}