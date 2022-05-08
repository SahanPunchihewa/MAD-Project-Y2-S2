package com.example.mobileapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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

public class BakeryMainActivity extends AppCompatActivity implements BakeryRVAdapter.BakeryClickInterface {

    private RecyclerView bakeryRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private ArrayList<BakeryRVModal> bakeryRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private BakeryRVAdapter bakeryRVAdapter;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery_main);

        bakeryRV =findViewById(R.id.idRVBakery);
        loadingPB=findViewById(R.id.idPBLoading);
        addFAB=findViewById(R.id.idAddFAB);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databasereference=firebaseDatabase.getReference("bakery_Items");
        bakeryRVModalArrayList = new ArrayList<>();
        bottomSheetRL=findViewById(R.id.idRLBSheet);
        mAuth=FirebaseAuth.getInstance();
        bakeryRVAdapter=new BakeryRVAdapter(bakeryRVModalArrayList,this,this);
        bakeryRV.setLayoutManager(new LinearLayoutManager(this));
        bakeryRV.setAdapter(bakeryRVAdapter);

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BakeryMainActivity.this, AddBakeryItemActivity.class));
            }
        });

        getAllCourses();
    }

    private void getAllCourses(){

        bakeryRVModalArrayList.clear();
        databasereference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //loadingPB.setVisibility(View.GONE);
                bakeryRVModalArrayList.add(snapshot.getValue(BakeryRVModal.class));
                bakeryRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                bakeryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                //loadingPB.setVisibility(View.GONE);
                bakeryRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //loadingPB.setVisibility(View.GONE);
                bakeryRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBakeryClick(int position) {

        displayBottomSheet(bakeryRVModalArrayList.get(position));
    }

    private void displayBottomSheet(BakeryRVModal bakeryRVModal) {
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

        ItemNameTV.setText(bakeryRVModal.getBakeryIName());
        ItemDescTV.setText(bakeryRVModal.getBakeryIDescription());

        ItemPriceTV.setText("Rs. "+bakeryRVModal.getBakeryIPrice());
        Picasso.get().load(bakeryRVModal.getBakeryI_Img()).into(ItemIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(BakeryMainActivity.this, EditBakeryItemsActivity.class);
                i.putExtra("bakery_Items", (Parcelable) bakeryRVModal);
                startActivity(i);
            }
        });

        viewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(bakeryRVModal.getBakeryI_Img()));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.idLogout:
                Toast.makeText(this, "User Logged out..", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i=new Intent(BakeryMainActivity.this ,PHManagerLogin.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}