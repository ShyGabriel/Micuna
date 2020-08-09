package com.example.micuna.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.micuna.R;
import com.example.micuna.modelo.Food;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {

    public Context c;
    public ArrayList<Food> arrayList;

    public MyAdapter(Context c, ArrayList<Food> arrayList){
        this.c=c;
        this.arrayList=arrayList;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item,parent,false);

        return new MyAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        Food categoryFood = arrayList.get(position);

        holder.namefood.setText(categoryFood.getName());
        holder.descfood.setText(categoryFood.getDescription());

    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView namefood, descfood;

        public MyAdapterViewHolder(View itemView) {
            super(itemView);

            namefood = itemView.findViewById(R.id.food_text);
            descfood = itemView.findViewById(R.id.desc_text);

        }
    }
}
