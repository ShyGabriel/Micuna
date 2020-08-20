package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.micuna.R;
import com.example.micuna.include.MyToolbar;
import com.example.micuna.modelo.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FoodDetail extends AppCompatActivity {
    Context context;
    TextView mName, mDescription, mPrice;
    private ImageView mPic;
    FloatingActionButton floatingActionButton;
    DatabaseReference ref;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        mName = findViewById(R.id.nameFoodDetail);
        mDescription = findViewById(R.id.descFoodDetail);
        mPrice = findViewById(R.id.priceFoodDetail);
        mPic = findViewById(R.id.picFoodDetail);
        floatingActionButton = findViewById(R.id.btn_cart);

        ref = FirebaseDatabase.getInstance().getReference().child("Foods");

        String FoodKey= getIntent().getStringExtra("FoodKey");

        ref.child(FoodKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();
                    String description = dataSnapshot.child("description").getValue().toString();
                    String price = dataSnapshot.child("price").getValue().toString();
                    String image = dataSnapshot.child("image").getValue().toString();

                    mName.setText(name);
                    mDescription.setText(description);
                    mPrice.setText(price);
                    Picasso.get().load(image).into(mPic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(FoodDetail.this, MapClientActivity.class);
                startActivity(intentA);
                Toast.makeText(FoodDetail.this, "Comida Añadida", Toast.LENGTH_SHORT).show();
            }
        });

/*
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuantityDialog(food);
            }
        });*/

    }

    double cost = 0;
    double finalCost = 0;
    int quantity = 0;

    private void showQuantityDialog(Food food) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quantity, null);

        TextView titleTv = view.findViewById(R.id.titleTv);
        TextView pQuantityTv = view.findViewById(R.id.pQuantityTv);
        TextView originalPriceTv = view.findViewById(R.id.originalPriceTv);
        TextView priceDiscountTv = view.findViewById(R.id.priceDiscountTv);
        final TextView finalTv = view.findViewById(R.id.finalTv);
        ImageButton decrementBtn = view.findViewById(R.id.decrementBtn);
        final TextView quantityTv = view.findViewById(R.id.quantityTv);
        ImageButton incrementBtn = view.findViewById(R.id.incrementBtn);
        Button continueBtn = findViewById(R.id.continueBtn);

        String productId = food.getMenuId();
        String title = food.getName();
        String discuntNote = food.getDiscount();
        String price = food.getPrice();

        //agregar condiciones
        cost = Double.parseDouble(price.replaceAll("S/",""));
        finalCost = Double.parseDouble(price.replaceAll("S/",""));
        quantity = 1;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        titleTv.setText(title);
        priceDiscountTv.setText("S/"+discuntNote);
        originalPriceTv.setText("S/"+price);
        quantityTv.setText(""+quantity);
        finalTv.setText("S/"+finalCost);

        AlertDialog dialog = builder.create();
        dialog.show();

        //incremento
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCost = finalCost + cost;
                quantity++;

                finalTv.setText("S/"+finalCost);
                quantityTv.setText(""+quantity);
            }
        });

        //decrecer
        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity>1){
                    finalCost = finalCost - cost;
                    quantity --;

                    finalTv.setText("S/"+finalCost);
                    quantityTv.setText(""+quantity);
                }
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetail.this,ListaClienteActivity.class);
            }
        });
    }
}
