package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.micuna.R;
import com.example.micuna.activities.LoginActivity;
import com.example.micuna.fragments.HomeFragment;
import com.example.micuna.fragments.OrderFragment;
import com.example.micuna.fragments.ProfileFragment;
import com.example.micuna.fragments.SearchFragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ContenidoCliente extends AppCompatActivity {

    private BottomNavigationView mBottonNavigation;
    private TextView usuario, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_cliente);

        usuario = findViewById(R.id.txtusuario);
        email = findViewById(R.id.txtemail);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null){
            String name = user.getDisplayName();
            String correo = user.getEmail();

            usuario.setText(name);
            email.setText(correo);
        }else {
            goLoginScreen();
        }

        //FRAGMENT PANTALLA DE INICIO
        showSelectedFragment(new HomeFragment());

        mBottonNavigation = (BottomNavigationView) findViewById(R.id.botonNavigation);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_home){
                    showSelectedFragment(new HomeFragment());
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

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
             /* ♦ METODO QUE PERMTE ELEGIR EL FRAGMENT ♦*/

    private void showSelectedFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


    }

}
