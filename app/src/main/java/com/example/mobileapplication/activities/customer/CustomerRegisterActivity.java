package com.example.mobileapplication.activities.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerRegisterActivity extends AppCompatActivity {

    private EditText name, emailaddress, phonenumber, password, cpassword;
    private Button regButton;
    private TextView loginTv;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        name = findViewById(R.id.etCusName);
        emailaddress = findViewById(R.id.etCusEmail);
        phonenumber = findViewById(R.id.etCusPhone);
        password = findViewById(R.id.etCusPass);
        cpassword= findViewById(R.id.etCusCPass);
        regButton = findViewById(R.id.RegBtn);
        loginTv = findViewById(R.id.TvLogin);
        mAuth = FirebaseAuth.getInstance();

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerRegisterActivity.this,CustomerLoginActivity.class);
                startActivity(intent);
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String EmailAddress = emailaddress.getText().toString();
                String PhoneNumber = phonenumber.getText().toString();
                String Password = password.getText().toString();
                String Cpassword = cpassword.getText().toString();

                if(!Password.equals(Cpassword)) {
                    Toast.makeText(CustomerRegisterActivity.this, "Both Password Should be Same", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Name) && TextUtils.isEmpty(EmailAddress) && TextUtils.isEmpty(PhoneNumber)){

                    Toast.makeText(CustomerRegisterActivity.this, "Please Added Your Details", Toast.LENGTH_SHORT).show();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(EmailAddress,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(CustomerRegisterActivity.this, "Customer Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(CustomerRegisterActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(CustomerRegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });
    }
}