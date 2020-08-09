package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.micuna.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListaClienteActivity extends AppCompatActivity {
    BottomNavigationView mBottonNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_lista);

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
