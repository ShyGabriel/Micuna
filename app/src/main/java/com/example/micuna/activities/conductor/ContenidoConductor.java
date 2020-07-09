package com.example.micuna.activities.conductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.micuna.R;
import com.example.micuna.activities.LoginActivity;
import com.example.micuna.fragments.OrdenesFragment;
import com.example.micuna.fragments.ProfileFragment;
import com.example.micuna.fragments.SearchFragment;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ContenidoConductor extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    BottomNavigationView mBottonNavigation;
    ImageView photoImageView;
    TextView nameTextView, emailTextView, idTextView;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    FirebaseAuth firebaseAuth;
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_conductor);

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
                    goLoginScreen();
                }
            }
        };

        showSelectedFragment(new OrdenesFragment());

        mBottonNavigation = (BottomNavigationView) findViewById(R.id.botonNavigation);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_ordenes){
                    showSelectedFragment(new OrdenesFragment());
                }

                if (menuItem.getItemId() == R.id.menu_search){
                    showSelectedFragment(new SearchFragment());
                }

                if (menuItem.getItemId() == R.id.menu_profile){
                    showSelectedFragment(new ProfileFragment());
                }

                return true;

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void showSelectedFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.containerConductor,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    private void setUserData(FirebaseUser user){
        nameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        idTextView.setText(user.getUid());
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }
    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logOutConductor(View view) {
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLoginScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo cerrar sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void revokeConductor(View view) {
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLoginScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "No se Revoco el acceso", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}
