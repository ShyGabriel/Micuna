package com.example.micuna.fragments;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.micuna.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

View view;

    public HomeFragment() {
        // Required empty public constructor
        // TU VIEJO EL PAULON CHAMIÑO

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

}
