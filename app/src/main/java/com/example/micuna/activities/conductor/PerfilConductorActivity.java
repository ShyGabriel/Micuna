package com.example.micuna.activities.conductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.micuna.R;
import com.example.micuna.activities.MainActivity;
import com.example.micuna.include.ContenidoToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilConductorActivity extends AppCompatActivity {
    private static final String TAGc = "ViewDatabase";
    BottomNavigationView mBottonNavigation;
    TextView mtxtnombre, mtxtcorreo, mtxtplaca, mtxtcarro;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_conductor);

        ContenidoToolbar.show(this);

        mtxtnombre = findViewById(R.id.txtUsuario);
        mtxtcorreo = findViewById(R.id.txtEmail);
        mtxtplaca = findViewById(R.id.txtPlaca);
        mtxtcarro = findViewById(R.id.txtCarro);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserId = user.getUid();

        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(UserId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String placa = dataSnapshot.child("vehiclePlate").getValue(String.class);
                String carro = dataSnapshot.child("vehicleBrand").getValue(String.class);

                mtxtnombre.setText(name);
                mtxtcorreo.setText(email);
                mtxtplaca.setText(placa);
                mtxtcarro.setText(carro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_profile_conductor);

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
                        startActivity(new Intent(getApplicationContext(),
                                MapConductorActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_profile_conductor:

                        return true;

                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.driver_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout_driver:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }

        return true;
    }
}
