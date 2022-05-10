package com.example.mobileapplication.activities.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.mobileapplication.R;
import com.example.mobileapplication.models.pharmacyRVModal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addPharmacyItemActivity extends AppCompatActivity {

    private TextInputEditText pharmacyINameEdt ,pharmacyIPriceEdt,pharmacyI_ImgEdt,pharmacyIDescEdt;
    private Button addPharmacyIBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String pharmacyI_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy_item);

        pharmacyINameEdt=findViewById(R.id.idEdtItemName);
        pharmacyIPriceEdt=findViewById(R.id.idEdtItemPrice);
        pharmacyI_ImgEdt=findViewById(R.id.idEdtItemImageLink);
        pharmacyIDescEdt=findViewById(R.id.idEdtItemDesc);
        addPharmacyIBtn=findViewById(R.id.idBtnAddItem);
        loadingPB=findViewById(R.id.idPBLoading);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Pharmacy_Items");

        addPharmacyIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.VISIBLE);
                String pharmacyIName =pharmacyINameEdt.getText().toString();
                String pharmacyIPrice =pharmacyIPriceEdt.getText().toString();
                String pharmacyI_Img =pharmacyI_ImgEdt.getText().toString();
                String pharmacyIDesc =pharmacyIDescEdt.getText().toString();

                pharmacyI_ID =pharmacyIName;

                pharmacyRVModal pharmacyRVModal =new pharmacyRVModal(pharmacyIName,pharmacyIDesc,pharmacyIPrice,pharmacyI_Img,pharmacyI_ID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(pharmacyI_ID).setValue(pharmacyRVModal);
                        Toast.makeText(addPharmacyItemActivity.this, "Item Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(addPharmacyItemActivity.this, MainActivityPH.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(addPharmacyItemActivity.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) ;
            }
        });


    }
}