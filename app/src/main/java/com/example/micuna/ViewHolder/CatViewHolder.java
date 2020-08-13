package com.example.micuna.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.micuna.R;

public class CatViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textname, textdesc;
    public View vi;

    public CatViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.menu_image);
        textname=itemView.findViewById(R.id.menu_categoria);
        textdesc=itemView.findViewById(R.id.menu_desc);

        vi = itemView;
    }


}
