package com.example.mobileapplication.adapter;

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

import com.example.mobileapplication.R;
import com.example.mobileapplication.models.groceryRVModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class groceryRVAdapter extends RecyclerView.Adapter<groceryRVAdapter.ViewHolder> {

    private ArrayList<groceryRVModal> groceryRVModalArrayList;
    private Context context;
    int lastPos= -1 ;
    private ItemClickInterface itemClickInterface;

    public groceryRVAdapter(ArrayList<groceryRVModal> groceryRVModalArrayList, Context context, groceryRVAdapter.ItemClickInterface itemClickInterface) {
        this.groceryRVModalArrayList = groceryRVModalArrayList;
        this.context = context;
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public groceryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.grocery_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull groceryRVAdapter.ViewHolder holder, int position) {


        groceryRVModal groceryRVModal =groceryRVModalArrayList.get(position);
        holder.groceryNameTV.setText(groceryRVModal.getGroceryName());
        holder.groceryPriceTV.setText("Rs. "+groceryRVModal.getGroceryPrice());
        Picasso.get().load(groceryRVModal.getGroceryImg()).into(holder.groceryIV);

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
        return groceryRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView groceryNameTV , groceryPriceTV ;
        private ImageView groceryIV;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            groceryNameTV=itemView.findViewById(R.id.idTVGroceryName);
            groceryPriceTV=itemView.findViewById(R.id.idTVPrice);
            groceryIV=itemView.findViewById(R.id.idIVGrocery);
        }

    }

    public interface ItemClickInterface{

        void onItemClick(int position);

    }

}

