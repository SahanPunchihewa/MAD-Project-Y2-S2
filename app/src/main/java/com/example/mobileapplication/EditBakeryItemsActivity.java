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

public class EditBakeryItemsActivity<PharmacyRVModal> extends AppCompatActivity {

    private TextInputEditText bakeryINameEdt ,bakeryIPriceEdt ,bakeryI_ImgEdt ,bakeryIDescEdt;
    private Button updateItemBtn , deleteItemBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String bakeryID;

    private  pharmacyRVModal pharmacyRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pharmacy_items);

        firebaseDatabase=FirebaseDatabase.getInstance();


        bakeryINameEdt=findViewById(R.id.idEdtItemName);
        bakeryIPriceEdt=findViewById(R.id.idEdtItemPrice);
        bakeryI_ImgEdt=findViewById(R.id.idEdtItemImageLink);
        bakeryIDescEdt=findViewById(R.id.idEdtItemDesc);
        updateItemBtn=findViewById(R.id.idBtnUpdateItem);
        deleteItemBtn=findViewById(R.id.idBtnDeleteItem);
        loadingPB=findViewById(R.id.idPBLoading);

        //getting data from previous activity
        pharmacyRVModal=getIntent().getParcelableExtra("Pharmacy_Items");
        if(pharmacyRVModal!=null){
            bakeryINameEdt.setText(pharmacyRVModal.getPharmacyIName() );
            bakeryIPriceEdt.setText(pharmacyRVModal.getPharmacyIPrice());
            bakeryI_ImgEdt.setText(pharmacyRVModal.getPharmacyI_Img());
            bakeryIDescEdt.setText(pharmacyRVModal.getPharmacyIDescription());
            bakeryID=pharmacyRVModal.getPharmacyI_ID();


        }

        databaseReference=firebaseDatabase.getReference("bakery_Items").child(bakeryID);


        updateItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //loadingPB.setVisibility(View.VISIBLE);

                String bakeryIName =bakeryINameEdt.getText().toString();
                String bakeryIPrice =bakeryIPriceEdt.getText().toString();
                String bakeryI_Img =bakeryI_ImgEdt.getText().toString();
                String bakeryIDesc =bakeryIDescEdt.getText().toString();

                Map<String,Object> map =new HashMap<>();

                map.put("ItemName" ,bakeryIName);
                map.put("ItemDescription" ,bakeryIDesc);
                map.put("ItemPrice" ,bakeryIPrice);
                map.put("ItemImg" ,bakeryI_Img);
                map.put("ItemID" ,bakeryID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditBakeryItemsActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditBakeryItemsActivity.this ,MainActivityPH.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(EditBakeryItemsActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(EditBakeryItemsActivity.this ,MainActivityPH.class));
    }



}