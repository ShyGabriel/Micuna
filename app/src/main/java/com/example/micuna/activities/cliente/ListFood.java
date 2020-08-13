package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.micuna.R;
import com.example.micuna.ViewHolder.DetailViewHolder;
import com.example.micuna.ViewHolder.FoodViewHolder;
import com.example.micuna.ViewHolder.MyAdapter;
import com.example.micuna.modelo.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFood extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    EditText editText;
    String categoryId="";
    ArrayList<Food> arrayList;
    FirebaseRecyclerOptions<Food> options;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);

        recyclerView = findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);

        //mostrar item de las listas
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null){
            loadListFood();
        }

        //mostrar datos buscados
        arrayList = new ArrayList<>();
        editText = findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()){
                    search(s.toString());
                }else {
                    search("");
                }
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Foods");
        options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(databaseReference,Food.class).build();

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, final int i, @NonNull Food food) {
                holder.textfood.setText(food.getName());

                holder.preciofood.setText(food.getPrice());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListFood.this, FoodDetail.class);
                        intent.putExtra("FoodKey",getRef(i).getKey());
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_item,parent,false);

                return new FoodViewHolder(view);
            }

        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void loadListFood() {
        options = new FirebaseRecyclerOptions.Builder<Food>().setQuery(databaseReference,Food.class).build();
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder foodholder, int i, @NonNull Food food) {

                foodholder.textfood.setText(food.getName());
                foodholder.preciofood.setText(food.getDescription());

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                databaseReference.orderByChild("menuId").equalTo(categoryId);
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);

                return new FoodViewHolder(v);
            }
        };

        recyclerView.setAdapter(adapter);

    }

    private void search(String ese) {

        Query query = databaseReference.orderByChild("name")
                .startAt(ese)
                .endAt(ese +"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(query,Food.class).build();

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, final int i, @NonNull Food food) {
                holder.textfood.setText(food.getName());
                holder.preciofood.setText(food.getPrice());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListFood.this, FoodDetail.class);
                        intent.putExtra("FoodKey",getRef(i).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_item,parent,false);

                return new FoodViewHolder(view);
            }

        };
         adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (adapter!=null)
            adapter.startListening();

    }

    @Override
    protected void onStop() {
        if (adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter!=null)
            adapter.startListening();

    }
}
