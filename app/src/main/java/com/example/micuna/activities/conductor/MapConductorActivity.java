package com.example.micuna.activities.conductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.micuna.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapConductorActivity extends AppCompatActivity {
    BottomNavigationView mBottonNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_conductor);


        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_map_conductor);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.menu_ordenes_conductor:
                        startActivity(new Intent(getApplicationContext(),
                                ContenidoConductor.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_map_conductor:
                        return true;

                    case R.id.menu_profile_conductor:
                        startActivity(new Intent(getApplicationContext(),
                                PerfilConductorActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });
    }
}
