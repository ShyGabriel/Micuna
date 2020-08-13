package com.example.micuna.activities.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.micuna.R;

import com.example.micuna.ViewHolder.CatViewHolder;
import com.example.micuna.activities.MainActivity;

import com.example.micuna.modelo.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ContenidoCliente extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    BottomNavigationView mBottonNavigation;
    TextView nameTextView, emailTextView, idTextView;
    ImageView photoImageView;
    GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<Category> options;
    FirebaseRecyclerAdapter<Category, CatViewHolder> adapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_cliente);

        Toolbar toolbar = findViewById(R.id.toolbar_contenido);
        setSupportActionBar(toolbar);

        //Recycler
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category");

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        LoadData();

        //Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();


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

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(databaseReference,Category.class).build();
        adapter = new FirebaseRecyclerAdapter<Category, CatViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CatViewHolder holder, final int i, @NonNull final Category category) {
                holder.textname.setText(category.getName());
                holder.textdesc.setText(category.getDescription());
                Picasso.get().load(category.getImage()).into(holder.imageView);

                holder.vi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContenidoCliente.this, ListFood.class);

                        intent.putExtra("CategoryId",adapter.getRef(i).getKey());
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);

                return new CatViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

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
