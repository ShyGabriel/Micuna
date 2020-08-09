package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.micuna.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilClienteActivity extends AppCompatActivity {
    BottomNavigationView mBottonNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_profile);

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
                        startActivity(new Intent(getApplicationContext(),
                                ListaClienteActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_profile:
                        return true;

                }

                return false;
            }
        });

    }
}
