package com.example.mobileapplication.activities.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobileapplication.R;

public class LoginActivity extends AppCompatActivity {

    public Button managerLogin;
    public Button customerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        managerLogin = findViewById(R.id.Login_Manager_btn);
        customerLogin=findViewById(R.id.Login_Customer_btn);
     //   managerLogin.setOnClickListener(new View.OnClickListener() {
      /*      @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ManagerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }); */

        customerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, Customer_login.class);
                startActivity(intent);
                finish();

            }
        });
    }
}