package com.example.mobileapplication.activities.bakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class BakeryManagerRegisterActivity extends AppCompatActivity {

    private EditText bakeryname, bakerypassword, bakerycpassword;
    private Button bakeryReg;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery_manager_register);

        bakeryname = findViewById(R.id.BakeryName);
        bakerypassword = findViewById(R.id.BakeryPass);
        bakerycpassword = findViewById(R.id.BakerycPass);
        bakeryReg = findViewById(R.id.RegBtnBakery);
        mAuth = FirebaseAuth.getInstance();

        bakeryReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = bakeryname.getText().toString();
                String password = bakerypassword.getText().toString();
                String cpassword = bakerycpassword.getText().toString();

                if(!password.equals(cpassword)){
                    Toast.makeText(BakeryManagerRegisterActivity.this, "Please Check Both Password", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(Name) && TextUtils.isEmpty(password)) {
                    Toast.makeText(BakeryManagerRegisterActivity.this, "Please add your details", Toast.LENGTH_LONG).show();
                }

                else {

                    mAuth.createUserWithEmailAndPassword(Name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(BakeryManagerRegisterActivity.this, "User Registered...", Toast.LENGTH_LONG).show();
                            }
                            else {

                                Toast.makeText(BakeryManagerRegisterActivity.this, "Failed to Register User...", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


    }
}