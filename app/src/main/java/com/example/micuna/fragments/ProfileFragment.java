package com.example.micuna.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.micuna.R;
import com.example.micuna.activities.LoginActivity;
import com.example.micuna.activities.MainActivity;
import com.example.micuna.activities.cliente.ContenidoCliente;
import com.example.micuna.modelo.Cliente;
import com.example.micuna.modelo.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ProfileFragment extends Fragment{
    BottomNavigationView mBottonNavigation;
    CircleImageView photoImageView;
    //ImageView photoImageView;
    TextView nameTextView, emailTextView, idTextView;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    GoogleApiClient googleApiClient;

    private View view;
    private RecyclerView recyclerView;
    private DatabaseReference userRef, uRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
/*
      //  recyclerView = view.findViewById(R.id.photoImageView);
        recyclerView = view.findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("User").child(currentUserID);
        uRef = FirebaseDatabase.getInstance().getReference().child("User");
        */

     /*   photoImageView = view.findViewById(R.id.photoImageView);
        nameTextView = view.findViewById(R.id.nameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        idTextView = view.findViewById(R.id.idTextView);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    setUserData(user);
                }
            }
        };*/

        return view;
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<User> options= new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(userRef, User.class)
                .build();

        FirebaseRecyclerAdapter<User, UserViewHolder> adapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, int i, @NonNull User user) {
                String userIDs = getRef(i).getKey();

                uRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("name")){
                            String uId = dataSnapshot.child("id").getValue().toString();
                            String uName = dataSnapshot.child("name").getValue().toString();
                            String uEmail = dataSnapshot.child("email").getValue().toString();
                         //   String uId = dataSnapshot.child("id").getValue().toString();

                            userViewHolder.userName.setText(uName);
                            userViewHolder.userEmail.setText(uEmail);
                            userViewHolder.userId.setText(uId);
                           // Picasso.get().load(uImage).placeholder(R.drawable.ic_profile).into(userViewHolder.userImage);

                        }else {
                            String uName = dataSnapshot.child("name").getValue().toString();
                            String uEmail = dataSnapshot.child("email").getValue().toString();
                            String uId = dataSnapshot.child("id").getValue().toString();

                            userViewHolder.userName.setText(uName);
                            userViewHolder.userEmail.setText(uEmail);
                            userViewHolder.userId.setText(uId);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_profile,parent,false);
                UserViewHolder viewHolder = new UserViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userName, userEmail, userId;
        CircleImageView userImage;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.nameTextView);
            userEmail = itemView.findViewById(R.id.emailTextView);
            userId = itemView.findViewById(R.id.idTextView);
          //  userImage = itemView.findViewById(R.id.photoImageView);
        }
    }*/
    /*
     private void setUserData(FirebaseUser user){
        nameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        idTextView.setText(user.getUid());
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }

*/
}
