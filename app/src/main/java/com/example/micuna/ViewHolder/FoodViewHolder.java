package com.example.micuna.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.micuna.R;
import com.example.micuna.interfaces.ItemClickListener;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    public TextView textfood, descfood;



    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        textfood = (TextView) itemView.findViewById(R.id.food_text);
        descfood = (TextView) itemView.findViewById(R.id.desc_text);

    }
}
