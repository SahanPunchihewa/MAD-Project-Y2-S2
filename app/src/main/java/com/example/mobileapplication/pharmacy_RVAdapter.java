package com.example.mobileapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class pharmacy_RVAdapter extends RecyclerView.Adapter<pharmacy_RVAdapter.ViewHolder> {

    private ArrayList<pharmacyRVModal> pharmacyRVModalArrayList;
    private Context context;
    int lastPos= -1 ;
    private ItemClickInterface itemClickInterface;

    public pharmacy_RVAdapter(ArrayList<pharmacyRVModal> pharmacyRVModalArrayList, Context context, pharmacy_RVAdapter.ItemClickInterface itemClickInterface) {
        this.pharmacyRVModalArrayList = pharmacyRVModalArrayList;
        this.context = context;
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public pharmacy_RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.pharmacy_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pharmacy_RVAdapter.ViewHolder holder, int position) {


        pharmacyRVModal pharmacyRVModal =pharmacyRVModalArrayList.get(position);
        holder.pharmacyINameTV.setText(pharmacyRVModal.getPharmacyIName());
        holder.pharmacyIPriceTV.setText("Rs. "+pharmacyRVModal.getPharmacyIPrice());
        Picasso.get().load(pharmacyRVModal.getPharmacyI_Img()).into(holder.pharmacyI_IV);

        setAnimation(holder.itemView ,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemClickInterface.onItemClick(holder.getAdapterPosition());

            }
        });



    }

    private void setAnimation(View itemView , int position){

        if(position>lastPos){

            Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos=position;
        }

    }

    @Override
    public int getItemCount() {
        return pharmacyRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView pharmacyINameTV , pharmacyIPriceTV ;
        private ImageView pharmacyI_IV;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            pharmacyINameTV=itemView.findViewById(R.id.idTVItemName);
            pharmacyIPriceTV=itemView.findViewById(R.id.idTVPrice);
            pharmacyI_IV=itemView.findViewById(R.id.idIVItem);
        }

    }

    public interface ItemClickInterface{

        void onItemClick(int position);

    }

}

