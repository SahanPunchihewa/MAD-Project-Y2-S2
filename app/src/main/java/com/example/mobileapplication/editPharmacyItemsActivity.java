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

import java.util.HashMap;
import java.util.Map;

public class editPharmacyItemsActivity<PharmacyRVModal> extends AppCompatActivity {

    private TextInputEditText pharmacyINameEdt ,pharmacyIPriceEdt ,pharmacyI_ImgEdt ,pharmacyIDescEdt;
    private Button updateItemBtn , deleteItemBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String pharmacyID;

    private  pharmacyRVModal pharmacyRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pharmacy_items);

        firebaseDatabase=FirebaseDatabase.getInstance();


        pharmacyINameEdt=findViewById(R.id.idEdtItemName);
        pharmacyIPriceEdt=findViewById(R.id.idEdtItemPrice);
        pharmacyI_ImgEdt=findViewById(R.id.idEdtItemImageLink);
        pharmacyIDescEdt=findViewById(R.id.idEdtItemDesc);
        updateItemBtn=findViewById(R.id.idBtnUpdateItem);
        deleteItemBtn=findViewById(R.id.idBtnDeleteItem);
        loadingPB=findViewById(R.id.idPBLoading);

        //getting data from previous activity
        pharmacyRVModal=getIntent().getParcelableExtra("Pharmacy_Items");
        if(pharmacyRVModal!=null){
            pharmacyINameEdt.setText(pharmacyRVModal.getPharmacyIName() );
            pharmacyIPriceEdt.setText(pharmacyRVModal.getPharmacyIPrice());
            pharmacyI_ImgEdt.setText(pharmacyRVModal.getPharmacyI_Img());
            pharmacyIDescEdt.setText(pharmacyRVModal.getPharmacyIDescription());
            pharmacyID=pharmacyRVModal.getPharmacyI_ID();


        }

        databaseReference=firebaseDatabase.getReference("Pharmacy_Items").child(pharmacyID);


        updateItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //loadingPB.setVisibility(View.VISIBLE);

                String pharmacyIName =pharmacyINameEdt.getText().toString();
                String pharmacyIPrice =pharmacyIPriceEdt.getText().toString();
                String pharmacyI_Img =pharmacyI_ImgEdt.getText().toString();
                String pharmacyIDesc =pharmacyIDescEdt.getText().toString();

                Map<String,Object> map =new HashMap<>();

                map.put("ItemName" ,pharmacyIName);
                map.put("ItemDescription" ,pharmacyIDesc);
                map.put("ItemPrice" ,pharmacyIPrice);
                map.put("ItemImg" ,pharmacyI_Img);
                map.put("ItemID" ,pharmacyID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(editPharmacyItemsActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(editPharmacyItemsActivity.this ,MainActivityPH.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(editPharmacyItemsActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        deleteItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });

    }

    private void deleteCourse(){

        databaseReference.removeValue();
        Toast.makeText(this, "Course Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(editPharmacyItemsActivity.this ,MainActivityPH.class));
    }



}