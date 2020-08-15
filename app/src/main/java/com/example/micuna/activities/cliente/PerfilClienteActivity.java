package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.micuna.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilClienteActivity extends AppCompatActivity {
    BottomNavigationView mBottonNavigation;
    TextView mtxtnombre, mtxtcorreo;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_profile);

        mtxtnombre = findViewById(R.id.txtUsuario);
        mtxtcorreo = findViewById(R.id.txtEmail);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("name").getValue(String.class);
                    String correo = dataSnapshot.child("email").getValue(String.class);

                    mtxtnombre.setText(nombre);
                    mtxtcorreo.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
