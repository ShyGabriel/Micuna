package com.example.micuna.ViewHolder;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.micuna.R;
import com.squareup.picasso.Picasso;

public class DetailViewHolder extends RecyclerView.ViewHolder {

    public TextView textfood, descfood, preciofood;
    public ImageView imageView;
    public View v;


    public DetailViewHolder(@NonNull View itemView) {
        super(itemView);
            textfood = itemView.findViewById(R.id.nameFoodDetail);
            descfood= itemView.findViewById(R.id.descFoodDetail);
            preciofood = itemView.findViewById(R.id.priceFoodDetail);
            imageView = itemView.findViewById(R.id.picFoodDetail);

            v = itemView;
    }


}
