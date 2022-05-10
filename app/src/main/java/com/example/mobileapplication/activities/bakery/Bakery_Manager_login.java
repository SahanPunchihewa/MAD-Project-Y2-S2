package com.example.mobileapplication.activities.bakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.MainActivity;
import com.example.mobileapplication.activities.pharmacy.MainActivityPH;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Bakery_Manager_login extends AppCompatActivity {
    private EditText userNameEdt, pwdEdt;
    private Button LoginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery_manager_login);
        userNameEdt = findViewById(R.id.bakeryEmail);
        pwdEdt = findViewById(R.id.BakeryPass);
        LoginBtn=findViewById(R.id.Bakery_login_btn);

        mAuth=FirebaseAuth.getInstance();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginBtn.setVisibility(View.GONE);
                String userName=userNameEdt.getText().toString();
                String pwd=pwdEdt.getText().toString();

                if(TextUtils.isEmpty(userName)&& TextUtils.isEmpty(pwd)) {
                    Toast.makeText(Bakery_Manager_login.this, "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                    return;

                }else {

                    mAuth.signInWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                LoginBtn.setVisibility(View.GONE);
                                Toast.makeText(Bakery_Manager_login.this, "Login Successfull!", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Bakery_Manager_login.this, BakeryMainActivity.class);
                                startActivity(i);
                                finish();

                            }else {
                                LoginBtn.setVisibility(View.GONE);
                                Toast.makeText(Bakery_Manager_login.this, "Fail to Login", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =mAuth.getCurrentUser();
        if(user!=null){

            Intent i=new Intent(Bakery_Manager_login.this , MainActivityPH.class);
            startActivity(i);
            this.finish();
        }



    }
}