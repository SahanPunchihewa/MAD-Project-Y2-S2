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

public class BakeryRVAdapter extends RecyclerView.Adapter<BakeryRVAdapter.ViewHolder> {

    private ArrayList<BakeryRVModal>  bakeryRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private BakeryClickInterface bakeryClickInterface;

    public BakeryRVAdapter(ArrayList<BakeryRVModal> bakeryRVModalArrayList, Context context, BakeryClickInterface bakeryClickInterface) {
        this.bakeryRVModalArrayList = bakeryRVModalArrayList;
        this.context = context;
        this.bakeryClickInterface = bakeryClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.bakery_rv_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            BakeryRVModal bakeryRVModal = bakeryRVModalArrayList.get(position);
            holder.bakeryNameTV.setText(bakeryRVModal.getBakeryIName());
            holder.bakeryPriceTV.setText("Rs. "+bakeryRVModal.getBakeryIPrice());
        Picasso.get().load(bakeryRVModal.getBakeryI_Img()).into(holder.bakeryIV);
        setAnimation(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Have Error on This Line
                bakeryClickInterface.onBakeryClick(holder.getAdapterPosition());
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if(position>lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }

    }

    @Override
    public int getItemCount() {
        return bakeryRVModalArrayList.size();
    }

    public interface  BakeryClickInterface{

        void onBakeryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView bakeryNameTV, bakeryPriceTV;
        private ImageView bakeryIV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bakeryNameTV = itemView.findViewById(R.id.idTVItemName);
            bakeryPriceTV  = itemView.findViewById(R.id.idTVPrice);
            bakeryIV = itemView.findViewById(R.id.idIVItem);

        }
    }

}
