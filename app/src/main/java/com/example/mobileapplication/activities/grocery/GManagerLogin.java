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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GManagerLogin extends AppCompatActivity {

    private TextInputEditText userNameEdt , pwdEdt;
    private Button LoginBtn;
    private ProgressBar loadingPB;
    private TextView registerTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmanager_login);
        userNameEdt=findViewById(R.id.idEdtUserName);
        pwdEdt=findViewById(R.id.idEdtPwd);
        LoginBtn=findViewById(R.id.idBtnLogin);
        loadingPB=findViewById(R.id.idPBLoading);
        registerTV=findViewById(R.id.idTVRegister);

        mAuth=FirebaseAuth.getInstance();

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(GManagerLogin.this , GManagerRegActivity.class);
                startActivity(i);

            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.GONE);
                String userName=userNameEdt.getText().toString();
                String pwd=pwdEdt.getText().toString();

                if(TextUtils.isEmpty(userName)&& TextUtils.isEmpty(pwd)){

                    Toast.makeText(GManagerLogin.this, "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    mAuth.signInWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(GManagerLogin.this, "Login Successfull!", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(GManagerLogin.this, MainActivityG.class);
                                startActivity(i);
                                finish();
                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(GManagerLogin.this, "Fail to Login", Toast.LENGTH_SHORT).show();

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

            Intent i=new Intent(GManagerLogin.this , MainActivityG.class);
            startActivity(i);
            this.finish();
        }

    }
}












