package com.example.mobileapplication.activities.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.customer.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GManagerRegActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt , pwdEdt ,cnfPwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmanager_reg);

        userNameEdt=findViewById(R.id.idEdtUserName);
        pwdEdt=findViewById(R.id.idEdtPwd);
        cnfPwdEdt=findViewById(R.id.idedCnPwd);
        registerBtn = findViewById(R.id.idBtnRegister);
        loadingPB = findViewById(R.id.idPBLoading);
        loginTV = findViewById(R.id.idTVLogin);

        mAuth=FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(GManagerRegActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String userName=userNameEdt.getText().toString();
                String pwd=pwdEdt.getText().toString();
                String cnfPwd=cnfPwdEdt.getText().toString();

                if(!pwd.equals(cnfPwd)){

                    Toast.makeText(GManagerRegActivity.this, "Passwords didn't match!", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)&& TextUtils.isEmpty(cnfPwd)){

                    Toast.makeText(GManagerRegActivity.this, "Some Required feilds are empty..", Toast.LENGTH_SHORT).show();
                }else{

                    mAuth.createUserWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(GManagerRegActivity.this, "User Registered Successfully..", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(GManagerRegActivity.this , MainActivityG.class);
                                startActivity(i);
                                finish();
                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(GManagerRegActivity.this, "Failed to register..", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });

                }
            }
        });



    }

}
