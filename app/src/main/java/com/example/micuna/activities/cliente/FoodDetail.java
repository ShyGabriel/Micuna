package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.micuna.R;
import com.example.micuna.include.MyToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView mName, mDescription, mPrice;
    private ImageView mPic;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        mName = findViewById(R.id.nameFoodDetail);
        mDescription = findViewById(R.id.descFoodDetail);
        mPrice = findViewById(R.id.priceFoodDetail);
        mPic = findViewById(R.id.picFoodDetail);

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
    }
}
