package com.example.micuna.activities.conductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.micuna.R;
import com.example.micuna.activities.LoginActivity;
import com.example.micuna.activities.MainActivity;
import com.example.micuna.fragments.OrdenesFragment;
import com.example.micuna.fragments.ProfileConductorFragment;
import com.example.micuna.fragments.ProfileFragment;
import com.example.micuna.fragments.SearchConductorFragment;
import com.example.micuna.fragments.SearchFragment;

import com.example.micuna.include.MyToolbar;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.AuthProvider;


public class ContenidoConductor extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    AuthProvider mAuthProvider;
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

        MyToolbar.show(this,"Conductor",true);

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

        showSelectedFragment(new OrdenesFragment());

        mBottonNavigation = findViewById(R.id.botonNavigation);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_ordenes_conductor){
                    showSelectedFragment(new OrdenesFragment());
                }

                if (menuItem.getItemId() == R.id.menu_search_conductor){
                    showSelectedFragment(new SearchConductorFragment());
                }

                if (menuItem.getItemId() == R.id.menu_profile_conductor){
                    showSelectedFragment(new ProfileConductorFragment());
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

    private void showSelectedFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.containerConductor,fragment)
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
