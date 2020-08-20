package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.micuna.R;
import com.example.micuna.ViewHolder.FoodViewHolder;
import com.example.micuna.modelo.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListaClienteActivity extends AppCompatActivity {
    BottomNavigationView mBottonNavigation;
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
        setContentView(R.layout.activity_lista_cliente);

        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_lista);

        database = FirebaseDatabase.getInstance();
        databaseReference=database.getReference("request");

        recyclerView = findViewById(R.id.recycler_cart);
        recyclerView.setHasFixedSize(true);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),
                                ContenidoCliente.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_lista:
                        return true;

                    case R.id.menu_profile:
                        startActivity(new Intent(getApplicationContext(),
                                PerfilClienteActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });
    }
}
