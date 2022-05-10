package com.example.mobileapplication.activities.pharmacy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mobileapplication.R;


import com.example.mobileapplication.adapter.pharmacy_RVAdapter;
import com.example.mobileapplication.models.pharmacyRVModal;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivityPH extends AppCompatActivity implements pharmacy_RVAdapter.ItemClickInterface {

    private RecyclerView pharmacyRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private ArrayList<pharmacyRVModal> pharmacyRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private pharmacy_RVAdapter pharmacyRVAdapter;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ph);

        pharmacyRV=findViewById(R.id.idRVPharmacy);
        loadingPB=findViewById(R.id.idPBLoading);
        addFAB=findViewById(R.id.idAddFAB);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databasereference=firebaseDatabase.getReference("Pharmacy_Items");
        pharmacyRVModalArrayList=new ArrayList<>();
        bottomSheetRL=findViewById(R.id.idRLBSheet);
        mAuth=FirebaseAuth.getInstance();
        pharmacyRVAdapter=new pharmacy_RVAdapter(pharmacyRVModalArrayList,this,this);
        pharmacyRV.setLayoutManager(new LinearLayoutManager(this));
        pharmacyRV.setAdapter(pharmacyRVAdapter);


        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivityPH.this ,addPharmacyItemActivity.class));

            }
        });

        getAllCourses();
    }

    private void getAllCourses(){

        pharmacyRVModalArrayList.clear();
        databasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                pharmacyRVModalArrayList.add(snapshot.getValue(pharmacyRVModal.class));
                pharmacyRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                pharmacyRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                //loadingPB.setVisibility(View.GONE);
                pharmacyRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                pharmacyRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        displayBottomSheet(pharmacyRVModalArrayList.get(position));

    }

    private void displayBottomSheet(pharmacyRVModal pharmacyRVModal){

        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView ItemNameTV=layout.findViewById(R.id.idTVItemName);
        TextView ItemDescTV=layout.findViewById(R.id.idTVDescription);
        TextView ItemPriceTV=layout.findViewById(R.id.idTVPrice);
        ImageView ItemIV=layout.findViewById(R.id.idIVItem);
        Button editBtn=layout.findViewById(R.id.idBtnEdit);
        Button viewDetailsBtn=layout.findViewById(R.id.idBtnViewDetails);


        ItemNameTV.setText(pharmacyRVModal.getPharmacyIName());
        ItemDescTV.setText(pharmacyRVModal.getPharmacyIDescription());

        ItemPriceTV.setText("Rs. "+pharmacyRVModal.getPharmacyIPrice());
        Picasso.get().load(pharmacyRVModal.getPharmacyI_Img()).into(ItemIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivityPH.this , editPharmacyItemsActivity.class);
                i.putExtra("Pharmacy_Items",pharmacyRVModal);
                startActivity(i);
            }
        });

        viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(pharmacyRVModal.getPharmacyI_Img()));
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch(id){
            case R.id.idLogout:
                Toast.makeText(this, "User Logged out..", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i=new Intent(MainActivityPH.this , PHManagerLogin.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}