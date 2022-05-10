package com.example.mobileapplication.activities.customer;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapplication.R;
import com.example.mobileapplication.activities.grocery.GManagerLogin;
import com.example.mobileapplication.activities.grocery.editGroceryActivity;
import com.example.mobileapplication.adapter.CustomerGroceryRVAdapter;
import com.example.mobileapplication.adapter.groceryRVAdapter;
import com.example.mobileapplication.models.groceryRVModal;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomerGroceryMainActivity extends AppCompatActivity implements CustomerGroceryRVAdapter.ItemClickInterface{

    private RecyclerView CustomerGroceryRV;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private ArrayList<groceryRVModal> groceryRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private CustomerGroceryRVAdapter customerGroceryRVAdapter;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_grocery_main);

        CustomerGroceryRV = findViewById(R.id.idRVCustomerGroceries);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasereference = firebaseDatabase.getReference("Groceries");
        groceryRVModalArrayList = new ArrayList<>();
        bottomSheetRL = findViewById(R.id.idRLBSheet_CustomerG);
        mAuth = FirebaseAuth.getInstance();
        customerGroceryRVAdapter = new CustomerGroceryRVAdapter(groceryRVModalArrayList, this, this);
        CustomerGroceryRV.setLayoutManager(new LinearLayoutManager(this));
        CustomerGroceryRV.setAdapter(customerGroceryRVAdapter);

        getAllGroceries();

    }
        private void getAllGroceries(){

            groceryRVModalArrayList.clear();
            databasereference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    //loadingPB.setVisibility(View.GONE);
                    groceryRVModalArrayList.add(snapshot.getValue(groceryRVModal.class));
                    customerGroceryRVAdapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    //loadingPB.setVisibility(View.GONE);
                    customerGroceryRVAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    //loadingPB.setVisibility(View.GONE);
                    customerGroceryRVAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    //loadingPB.setVisibility(View.GONE);
                    customerGroceryRVAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


        public void onItemClick(int position) {

            displayBottomSheet(groceryRVModalArrayList.get(position));
        }

        private void displayBottomSheet(groceryRVModal groceryRVModal){

            final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
            View layout = LayoutInflater.from(this).inflate(R.layout.customer_bottom_sheet,bottomSheetRL);
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

                    Intent i=new Intent(CustomerGroceryMainActivity.this , PaymentActivity.class);
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
                    Intent i=new Intent(CustomerGroceryMainActivity.this , GManagerLogin.class);
                    startActivity(i);
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
    }
}