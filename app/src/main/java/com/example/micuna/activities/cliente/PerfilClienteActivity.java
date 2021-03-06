package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.micuna.R;
import com.example.micuna.activities.MainActivity;
import com.example.micuna.include.ContenidoToolbar;
import com.example.micuna.modelo.Cliente;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class PerfilClienteActivity extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";
    BottomNavigationView mBottonNavigation;
    TextView mtxtnombre, mtxtcorreo;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        ContenidoToolbar.show(this);

        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_profile);

        mtxtnombre = findViewById(R.id.txtUsuario);
        mtxtcorreo = findViewById(R.id.txtEmail);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        UserId = user.getUid();

        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients").child(UserId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);

                mtxtnombre.setText(name);
                mtxtcorreo.setText(email);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.client_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }

        return true;
    }

}
