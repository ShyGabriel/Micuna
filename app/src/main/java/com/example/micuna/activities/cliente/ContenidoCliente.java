package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.micuna.R;

import com.example.micuna.ViewHolder.CategoryAdapter;
import com.example.micuna.activities.MainActivity;

import com.example.micuna.fragments.HomewFragment;
import com.example.micuna.fragments.OrdenesFragment;
import com.example.micuna.fragments.OrderFragment;
import com.example.micuna.fragments.ProfileConductorFragment;
import com.example.micuna.fragments.ProfileFragment;
import com.example.micuna.fragments.SearchConductorFragment;
import com.example.micuna.fragments.SearchFragment;
import com.example.micuna.include.ContenidoToolbar;
import com.example.micuna.interfaces.ItemClickListener;
import com.example.micuna.interfaces.iComunicaFragment;
import com.example.micuna.modelo.Category;
import com.example.micuna.modelo.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContenidoCliente extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    BottomNavigationView mBottonNavigation;
    TextView nameTextView, emailTextView, idTextView;
    ImageView photoImageView;
    GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth;
    private RecyclerView recycler_menu;
    CategoryAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_cliente);

        Toolbar toolbar = findViewById(R.id.toolbar_contenido);
        setSupportActionBar(toolbar);

        //Recycler
        recycler_menu = findViewById(R.id.recycler);
        recycler_menu.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(manager);


        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Category"),Category.class)
                .build();

        cAdapter = new CategoryAdapter(options);
        recycler_menu.setAdapter(cAdapter);

        cAdapter.setOnClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent foodlist = new Intent(ContenidoCliente.this,ListFood.class);
                foodlist.putExtra("CategoryId",cAdapter.getRef(position).getKey());
                startActivity(foodlist);
            }
        });

        //Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
   /*     firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    setUserData(user);
                }else {
                    goMainScreen();
                }
            }
        };*/


     /*   showSelectedFragment(new HomewFragment());

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
        });*/

        mBottonNavigation = findViewById(R.id.botonNavigation);
        mBottonNavigation.setSelectedItemId(R.id.menu_home);

        mBottonNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.menu_home:
                        return true;

                    case R.id.menu_lista:
                        startActivity(new Intent(getApplicationContext(),
                                ListaClienteActivity.class));
                        overridePendingTransition(0,0);
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

    @Override
    protected void onStart() {
        super.onStart();
        cAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cAdapter.stopListening();
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




    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
