package com.example.mobileapplication.activities.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginActivity extends AppCompatActivity {

    private EditText emailaddress, password;
    private Button logBtn;
    private TextView tvReg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        emailaddress = findViewById(R.id.cusEmail);
        password =findViewById(R.id.cusPass);
        logBtn = findViewById(R.id.Cus_login_btn);
        tvReg = findViewById(R.id.TvReg);
        mAuth = FirebaseAuth.getInstance();

        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, CustomerRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailAddress =emailaddress.getText().toString();
                String Password = password.getText().toString();

                if(TextUtils.isEmpty(EmailAddress) && TextUtils.isEmpty(Password)){
                    Toast.makeText(CustomerLoginActivity.this, "Please Enter Your Login Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(EmailAddress,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(CustomerLoginActivity.this, "Login Successful...!!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomerLoginActivity.this, CustomerDashboardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(CustomerLoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}