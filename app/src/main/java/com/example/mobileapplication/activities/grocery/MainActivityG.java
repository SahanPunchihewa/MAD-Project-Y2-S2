package com.example.mobileapplication.activities.grocery;

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
import com.example.mobileapplication.activities.pharmacy.addPharmacyItemActivity;
import com.example.mobileapplication.models.groceryRVModal;
import com.example.mobileapplication.adapter.groceryRVAdapter;
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

public class MainActivityG extends AppCompatActivity implements groceryRVAdapter.ItemClickInterface {

    private RecyclerView groceryRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private ArrayList<groceryRVModal> groceryRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private groceryRVAdapter groceryRVAdapter;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_g);

        groceryRV=findViewById(R.id.idRVGroceries);
        loadingPB=findViewById(R.id.idPBLoading);
        addFAB=findViewById(R.id.idAddFAB);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databasereference=firebaseDatabase.getReference("Groceries");
        groceryRVModalArrayList=new ArrayList<>();
        bottomSheetRL=findViewById(R.id.idRLBSheet_g);
        mAuth=FirebaseAuth.getInstance();
        groceryRVAdapter=new groceryRVAdapter(groceryRVModalArrayList,this,this);
        groceryRV.setLayoutManager(new LinearLayoutManager(this));
        groceryRV.setAdapter(groceryRVAdapter);


        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivityG.this , addPharmacyItemActivity.class));

            }
        });

        getAllGroceries();
    }

    private void getAllGroceries(){

        groceryRVModalArrayList.clear();
        databasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                groceryRVModalArrayList.add(snapshot.getValue(groceryRVModal.class));
                groceryRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                groceryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                //loadingPB.setVisibility(View.GONE);
                groceryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                groceryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        displayBottomSheet(groceryRVModalArrayList.get(position));

    }

    private void displayBottomSheet(groceryRVModal groceryRVModal){

        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog_g,bottomSheetRL);
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


        ItemNameTV.setText(groceryRVModal.getGroceryName());
        ItemDescTV.setText(groceryRVModal.getGroceryDescription());

        ItemPriceTV.setText("Rs. "+groceryRVModal.getGroceryPrice());
        Picasso.get().load(groceryRVModal.getGroceryImg()).into(ItemIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivityG.this , editGroceryActivity.class);
                i.putExtra("groceries",groceryRVModal);
                startActivity(i);
            }
        });

        viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(groceryRVModal.getGroceryImg()));
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
                Intent i=new Intent(MainActivityG.this ,GManagerLogin.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}