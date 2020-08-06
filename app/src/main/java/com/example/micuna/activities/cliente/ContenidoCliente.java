package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.micuna.R;
import com.example.micuna.activities.MainActivity;
import com.example.micuna.comidas.antojos;
import com.example.micuna.comidas.bebidas;
import com.example.micuna.comidas.donuts;
import com.example.micuna.comidas.fast_food;
import com.example.micuna.comidas.favoritos;
import com.example.micuna.comidas.gaseosa;
import com.example.micuna.comidas.hamburguesa;
import com.example.micuna.comidas.peruanito;
import com.example.micuna.comidas.pizza;
import com.example.micuna.comidas.pollo_brasa;
import com.example.micuna.comidas.tacos;
import com.example.micuna.fragments.HomewFragment;
import com.example.micuna.fragments.OrderFragment;
import com.example.micuna.fragments.ProfileFragment;
import com.example.micuna.fragments.SearchFragment;
import com.example.micuna.include.ContenidoToolbar;
import com.example.micuna.interfaces.iComunicaFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContenidoCliente extends AppCompatActivity implements iComunicaFragment, GoogleApiClient.OnConnectionFailedListener {

    BottomNavigationView mBottonNavigation;
    TextView nameTextView, emailTextView, idTextView;
    ImageView photoImageView;
    GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_cliente);

        ContenidoToolbar.show(this);
        //MyToolbar.show(this,"Cliente",true);

        photoImageView = findViewById(R.id.photoImageView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        idTextView = findViewById(R.id.idTextView);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    setUserData(user);
                }else {
                    goMainScreen();
                }
            }
        };

        //FRAGMENT PANTALLA DE INICIO
        showSelectedFragment(new HomewFragment());

        mBottonNavigation = (BottomNavigationView) findViewById(R.id.botonNavigation);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_home){
                    showSelectedFragment(new HomewFragment());
                }

                if (menuItem.getItemId() == R.id.menu_search){
                    showSelectedFragment(new SearchFragment());

                }

                if (menuItem.getItemId() == R.id.menu_order){
                    showSelectedFragment(new OrderFragment());
                }

                if (menuItem.getItemId() == R.id.menu_profile){
                    showSelectedFragment(new ProfileFragment());
                }

                return true;
            }
        });
    }


    private void setUserData(FirebaseUser user){
        nameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        idTextView.setText(user.getUid());
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


             /* ♦ METODO QUE PERMTE ELEGIR EL FRAGMENT ♦*/

    private void showSelectedFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
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

    @Override
    public void pollo() {
        Intent intent = new Intent(ContenidoCliente.this, pollo_brasa.class);
        startActivity(intent);
    }

    @Override
    public void pizza() {
        Intent intent = new Intent(ContenidoCliente.this, pizza.class);
        startActivity(intent);
    }

    @Override
    public void donuts() {
        Intent intent = new Intent(ContenidoCliente.this, donuts.class);
        startActivity(intent);
    }

    @Override
    public void hamburguesa() {
        Intent intent = new Intent(ContenidoCliente.this, hamburguesa.class);
        startActivity(intent);
    }

    @Override
    public void gaseosa() {
        Intent intent = new Intent(ContenidoCliente.this, gaseosa.class);
        startActivity(intent);
    }

    @Override
    public void tacos() {
        Intent intent = new Intent(ContenidoCliente.this, tacos.class);
        startActivity(intent);
    }

    @Override
    public void favoritos() {
        Intent intent = new Intent(ContenidoCliente.this, favoritos.class);
        startActivity(intent);
    }

    @Override
    public void peruanito(){
        Intent intent = new Intent(ContenidoCliente.this, peruanito.class);
        startActivity(intent);
    }

    @Override
    public void fastfood() {
        Intent intent = new Intent(ContenidoCliente.this, fast_food.class);
        startActivity(intent);
    }

    @Override
    public void antojos() {
        Intent intent = new Intent(ContenidoCliente.this, antojos.class);
        startActivity(intent);
    }

    @Override
    public void bebidas() {
        Intent intent = new Intent(ContenidoCliente.this, bebidas.class);
        startActivity(intent);
    }


}
