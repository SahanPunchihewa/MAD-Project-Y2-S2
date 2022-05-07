/*package com.example.mobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name, et_email, et_phone, et_password, et_confirmPassword;

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        validateFields();
    }

    private void validateFields() {

        String name, email, phone, password, confirm_password;

        name = et_name.getText().toString();
        email = et_email.getText().toString();
        phone = et_phone.getText().toString();
        password = et_password.getText().toString();
        confirm_password = et_confirmPassword.getText().toString();
        // validation
        RegisterUser(name, email,password);
    }

    private void RegisterUser(String name, String email, String password) {
            mAuth.createUserWithEmailAndPassword()

    }
} */