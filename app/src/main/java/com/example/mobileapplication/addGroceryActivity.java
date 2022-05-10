package com.example.mobileapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addGroceryActivity extends AppCompatActivity {

    private TextInputEditText groceryNameEdt ,groceryPriceEdt ,groceryImgEdt ,groceryDescEdt;
    private Button addGroceryBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String groceryID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);

        groceryNameEdt=findViewById(R.id.idEdtGroceryName);
        groceryPriceEdt=findViewById(R.id.idEdtGroceryPrice);
        groceryImgEdt=findViewById(R.id.idEdtGroceryImageLink);
        groceryDescEdt=findViewById(R.id.idEdtGroceryDesc);
        addGroceryBtn=findViewById(R.id.idBtnAddGrocery);
        loadingPB=findViewById(R.id.idPBLoading);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Groceries");

        addGroceryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.VISIBLE);
                String groceryName =groceryNameEdt.getText().toString();
                String groceryPrice =groceryPriceEdt.getText().toString();
                String groceryImg =groceryImgEdt.getText().toString();
                String groceryDesc =groceryDescEdt.getText().toString();

                groceryID =groceryName;

                groceryRVModal groceryRvModal =new groceryRVModal(groceryName,groceryDesc,groceryPrice,groceryImg,groceryID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(groceryID).setValue(groceryRvModal);
                        Toast.makeText(addGroceryActivity.this, "Grocery Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(addGroceryActivity.this,MainActivityG.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(addGroceryActivity.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) ;
            }
        });


    }
}