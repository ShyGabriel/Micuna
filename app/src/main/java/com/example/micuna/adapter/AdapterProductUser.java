package com.example.micuna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.micuna.R;
import com.example.micuna.modelo.Food;

import java.util.ArrayList;

public class AdapterProductUser extends RecyclerView.Adapter<AdapterProductUser.HolderProductUser> {

    private Context context;
    public ArrayList<Food> productFoods;

    public AdapterProductUser(Context context, ArrayList<Food> productFoods) {
        this.context = context;
        this.productFoods = productFoods;
    }

    @NonNull
    @Override
    public HolderProductUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_item,parent,false);

        return new HolderProductUser(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductUser holder, int position) {
        Food food = productFoods.get(position);
        String textFood = food.getName();
        String precioFood = food.getPrice();
        String discountFood = food.getDiscount();
        String idFood = food.getMenuId();
        String descripcionFood = food.getDescription();
        String iconFood = food.getImage();

        holder.textfood.setText(textFood);
        holder.preciofood.setText(precioFood);

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productFoods.size();
    }

    class HolderProductUser extends RecyclerView.ViewHolder{

        private TextView textfood, descfood, preciofood, addToCart;
        private ImageView imageView;

        public HolderProductUser(@NonNull View itemView) {
            super(itemView);

            textfood = itemView.findViewById(R.id.food_text);
            preciofood = itemView.findViewById(R.id.food_price);
            addToCart = itemView.findViewById(R.id.addToCart);

        }
    }

}
