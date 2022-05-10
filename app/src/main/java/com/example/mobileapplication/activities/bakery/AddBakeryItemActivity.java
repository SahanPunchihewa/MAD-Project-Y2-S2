package com.example.mobileapplication.activities.bakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.pharmacy.MainActivityPH;
import com.example.mobileapplication.models.pharmacyRVModal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  AddBakeryItemActivity extends AppCompatActivity {

    private TextInputEditText bakeryINameEdt ,bakeryIPriceEdt,bakeryI_ImgEdt,bakeryIDescEdt;
    private Button addbakeryIBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String bakeryI_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy_item);

        bakeryINameEdt=findViewById(R.id.idEdtItemName);
        bakeryIPriceEdt=findViewById(R.id.idEdtItemPrice);
        bakeryI_ImgEdt=findViewById(R.id.idEdtItemImageLink);
        bakeryIDescEdt=findViewById(R.id.idEdtItemDesc);
        addbakeryIBtn=findViewById(R.id.idBtnAddItem);
        loadingPB=findViewById(R.id.idPBLoading);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Pharmacy_Items");

        addbakeryIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.VISIBLE);
                String bakeryIName =bakeryINameEdt.getText().toString();
                String bakeryIPrice =bakeryIPriceEdt.getText().toString();
                String bakeryI_Img =bakeryI_ImgEdt.getText().toString();
                String bakeryIDesc =bakeryIDescEdt.getText().toString();

                bakeryI_ID =bakeryIName;

                pharmacyRVModal pharmacyRVModal =new pharmacyRVModal(bakeryIName,bakeryIDesc,bakeryIPrice,bakeryI_Img,bakeryI_ID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(bakeryI_ID).setValue(pharmacyRVModal);
                        Toast.makeText(AddBakeryItemActivity.this, "Item Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddBakeryItemActivity.this, MainActivityPH.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(AddBakeryItemActivity.this, "Error is"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) ;
            }
        });


    }
}