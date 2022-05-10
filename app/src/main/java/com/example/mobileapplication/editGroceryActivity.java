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

public class editGroceryActivity<GroceryRVModal> extends AppCompatActivity {

    private TextInputEditText groceryNameEdt ,groceryPriceEdt ,groceryImgEdt ,groceryDescEdt;
    private Button updateItemBtn , deleteItemBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String groceryID;

    private  groceryRVModal groceryRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);

        firebaseDatabase=FirebaseDatabase.getInstance();


        groceryNameEdt=findViewById(R.id.idEdtGroceryName);
        groceryPriceEdt=findViewById(R.id.idEdtGroceryPrice);
        groceryImgEdt=findViewById(R.id.idEdtGroceryImageLink);
        groceryDescEdt=findViewById(R.id.idEdtGroceryDesc);
        updateItemBtn=findViewById(R.id.idBtnUpdateGrocery);
        deleteItemBtn=findViewById(R.id.idBtnDeleteGrocery);
        loadingPB=findViewById(R.id.idPBLoading);

        //getting data from previous activity
        groceryRVModal=getIntent().getParcelableExtra("groceries");
        if(groceryRVModal!=null){
            groceryNameEdt.setText(groceryRVModal.getGroceryName());
            groceryPriceEdt.setText(groceryRVModal.getGroceryPrice());
            groceryImgEdt.setText(groceryRVModal.getGroceryImg());
            groceryDescEdt.setText(groceryRVModal.getGroceryDescription());
            groceryID=groceryRVModal.getGroceryID();


        }

        databaseReference=firebaseDatabase.getReference("Groceries").child(groceryID);


        updateItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingPB.setVisibility(View.VISIBLE);

                String groceryName =groceryNameEdt.getText().toString();
                String groceryPrice =groceryPriceEdt.getText().toString();
                String groceryImg =groceryImgEdt.getText().toString();
                String groceryDesc =groceryDescEdt.getText().toString();

                Map<String,Object> map =new HashMap<>();

                map.put("groceryName" ,groceryName);
                map.put("groceryDescription" ,groceryDesc);
                map.put("groceryPrice" ,groceryPrice);
                map.put("groceryImg" ,groceryImg);
                map.put("groceryID" ,groceryID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(editGroceryActivity.this, "Grocery Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(editGroceryActivity.this ,MainActivityG.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(editGroceryActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(editGroceryActivity.this ,MainActivityG.class));
    }



}