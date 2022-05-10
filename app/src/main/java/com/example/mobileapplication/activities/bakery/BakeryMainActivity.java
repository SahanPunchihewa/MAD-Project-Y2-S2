package com.example.mobileapplication.activities.bakery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.grocery.GManagerLogin;
import com.example.mobileapplication.activities.grocery.MainActivityG;
import com.example.mobileapplication.activities.grocery.editGroceryActivity;
import com.example.mobileapplication.activities.pharmacy.addPharmacyItemActivity;
import com.example.mobileapplication.adapter.BakeryRVAdapter;
import com.example.mobileapplication.adapter.groceryRVAdapter;
import com.example.mobileapplication.models.BakeryModel;
import com.example.mobileapplication.models.groceryRVModal;
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

public class BakeryMainActivity extends AppCompatActivity implements BakeryRVAdapter.ItemClickInterface {

    private RecyclerView bakeryRV;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private ArrayList<BakeryModel> bakeryModelArrayList;
    private RelativeLayout bottomSheetRL;
    private BakeryRVAdapter bakeryRVAdapter;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery_main);

        bakeryRV = findViewById(R.id.idRVBakery);
        addFAB = findViewById(R.id.idAddFAB);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasereference = firebaseDatabase.getReference("Bakery");
        bakeryModelArrayList = new ArrayList<>();
        bottomSheetRL = findViewById(R.id.idRLBSheet_g);
        mAuth = FirebaseAuth.getInstance();
        bakeryRVAdapter = new BakeryRVAdapter(bakeryModelArrayList, this, this);
        bakeryRV.setAdapter(bakeryRVAdapter);

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(BakeryMainActivity.this, AddBakeryItemActivity.class));
            }
        });
        getAllBakery();
    }

    private void getAllBakery() {

        bakeryModelArrayList.clear();
        databasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                bakeryModelArrayList.add(snapshot.getValue(BakeryModel.class));
                bakeryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                bakeryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                bakeryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                bakeryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemClick(int position) {
        displayBottomSheet(bakeryModelArrayList.get(position));
    }

    private void displayBottomSheet(BakeryModel bakeryModel) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog_g, bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView ItemNameTV = layout.findViewById(R.id.idTVItemName);
        TextView ItemDescTV = layout.findViewById(R.id.idTVDescription);
        TextView ItemPriceTV = layout.findViewById(R.id.idTVPrice);
        ImageView ItemIV = layout.findViewById(R.id.idIVItem);
        Button editBtn = layout.findViewById(R.id.idBtnEdit);
        Button viewDetailsBtn = layout.findViewById(R.id.idBtnViewDetails);

        ItemNameTV.setText(bakeryModel.getBakeryName());
        ItemDescTV.setText(bakeryModel.getBakeryDescription());
        ItemPriceTV.setText("Rs. " + bakeryModel.getBakeryPrice());
        Picasso.get().load(bakeryModel.getBakeryImg()).into(ItemIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BakeryMainActivity.this, EditBakeryActivity.class);
                i.putExtra("Bakery", bakeryModel);
                startActivity(i);

            }
        });

        viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(bakeryModel.getBakeryImg()));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.idLogout:
                Toast.makeText(this, "User Logged out..", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(BakeryMainActivity.this, Bakery_Manager_login.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}