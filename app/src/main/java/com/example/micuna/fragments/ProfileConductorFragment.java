package com.example.micuna.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.micuna.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileConductorFragment extends Fragment {
    BottomNavigationView mBottonNavigation;
    CircleImageView photoImageView;
    //ImageView photoImageView;
    TextView nameTextView, emailTextView, idTextView;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    FirebaseAuth firebaseAuth;
    GoogleApiClient googleApiClient;
    public ProfileConductorFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_conductor, container, false);



        return view;
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }
    private void setUserData(FirebaseUser user){
        nameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        idTextView.setText(user.getUid());
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }*/
}
