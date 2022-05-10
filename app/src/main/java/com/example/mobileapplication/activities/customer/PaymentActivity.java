package com.example.mobileapplication.activities.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.models.PaymentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardHName, cardNumber, expireDate, cvv;
    private Button payButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cardHName = findViewById(R.id.cardNumber);
        cardNumber = findViewById(R.id.cardNumber);
        expireDate = findViewById(R.id.ExpireDate);
        cvv = findViewById(R.id.cvv);
        payButton = findViewById(R.id.payBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Payment");

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String CardHolderName = cardHName.getText().toString();
                String CardNumber = cardNumber.getText().toString();
                String ExpireDate =  expireDate.getText().toString();
                String Cvv = cvv.getText().toString();
                id = CardHolderName;

                PaymentModel paymentModel = new PaymentModel(CardHolderName, CardNumber,ExpireDate,Cvv, id);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(id).setValue(paymentModel);
                        Toast.makeText(PaymentActivity.this, "Payment Sucessfull...", Toast.LENGTH_SHORT).show();
                        // Need to implement Start Activity
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(PaymentActivity.this, "Error Please Check your Card Details", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}