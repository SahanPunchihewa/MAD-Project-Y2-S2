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
import com.example.mobileapplication.models.BakeryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BakeryRVAdapter extends RecyclerView.Adapter<BakeryRVAdapter.ViewHolder> {

    private ArrayList<BakeryModel> bakeryRVAdapterArrayList;
    private Context context;
    int lastPos= -1 ;
    private ItemClickInterface itemClickInterface;

    public BakeryRVAdapter(ArrayList<BakeryModel> bakeryRVAdapterArrayList, Context context, ItemClickInterface itemClickInterface) {
        this.bakeryRVAdapterArrayList = bakeryRVAdapterArrayList;
        this.context = context;
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.bakery_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BakeryModel bakerymodel = bakeryRVAdapterArrayList.get(position);
        holder.bakeryNameTV.setText(bakerymodel.getBakeryName());
        holder.bakeryPriceTV.setText("Rs. "+bakerymodel.getBakeryPrice());
        Picasso.get().load(bakerymodel.getBakeryImg()).into(holder.bakeryIV);

        setAnimation(holder.itemView ,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemClickInterface.onItemClick(holder.getAdapterPosition());

            }
        });
    }


    private void setAnimation(View itemView , int position) {

        if (position > lastPos) {

            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }

    }



    @Override
    public int getItemCount() {

        return bakeryRVAdapterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView bakeryNameTV , bakeryPriceTV ;
        private ImageView bakeryIV;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            bakeryNameTV=itemView.findViewById(R.id.idTVBakeryName);
            bakeryPriceTV=itemView.findViewById(R.id.idTVPriceBakery);
            bakeryIV=itemView.findViewById(R.id.idIVItemBakery);
        }

    }

    public interface ItemClickInterface{

        void onItemClick(int position);

    }
}
