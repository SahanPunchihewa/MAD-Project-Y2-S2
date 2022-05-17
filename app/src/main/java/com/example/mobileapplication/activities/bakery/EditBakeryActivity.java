package com.example.mobileapplication.activities.bakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.grocery.MainActivityG;
import com.example.mobileapplication.activities.grocery.editGroceryActivity;
import com.example.mobileapplication.models.BakeryModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditBakeryActivity extends AppCompatActivity {

    private TextInputEditText bakeryNameEdt ,bakeryPriceEdt ,bakeryImgEdt ,bakeryDescEdt;
    private Button updateBakeryBtn, deleteBakeryBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String bakeryID;

    private BakeryModel bakeryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bakery);
        firebaseDatabase=FirebaseDatabase.getInstance();

        bakeryNameEdt = findViewById(R.id.idEdtBakeryName);
        bakeryPriceEdt = findViewById(R.id.idEdtBakeryPrice);
        bakeryImgEdt = findViewById(R.id.idEdtBakeryImageLink);
        bakeryDescEdt = findViewById(R.id.idEdtGBakeryDesc);
        updateBakeryBtn = findViewById(R.id.idBtnUpdateBakery);
        deleteBakeryBtn = findViewById(R.id.idBtnDeleteBakery);

        bakeryModel = getIntent().getParcelableExtra("Bakery");
        if(bakeryModel !=null) {
            bakeryNameEdt.setText(bakeryModel.getBakeryName());
            bakeryPriceEdt.setText(bakeryModel.getBakeryPrice());
            bakeryImgEdt.setText(bakeryModel.getBakeryImg());
            bakeryDescEdt.setText(bakeryModel.getBakeryDescription());
            bakeryID = bakeryModel.getBakeryID();
        }

        databaseReference = firebaseDatabase.getReference("Bakery").child(bakeryID);

        updateBakeryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bakeryName = bakeryNameEdt.getText().toString();
                String bakeryPrice = bakeryPriceEdt.getText().toString();
                String bakeryImg = bakeryImgEdt.getText().toString();
                String bakeryDescription = bakeryDescEdt.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("bakeryName",bakeryName);
                map.put("bakeryPrice",bakeryPrice);
                map.put("bakeryName",bakeryName);
                map.put("bakeryImg",bakeryImg);
                map.put("bakeryDescription",bakeryDescription);
                map.put("bakeryID",bakeryID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditBakeryActivity.this, "Bakery Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditBakeryActivity.this , BakeryMainActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditBakeryActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });
                deleteBakeryBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteBakery();
                    }
                });

    }

    private void deleteBakery() {

        databaseReference.removeValue();
        Toast.makeText(this, "Bakery Item Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditBakeryActivity.this , BakeryMainActivity.class));
        // Intent
    }
}