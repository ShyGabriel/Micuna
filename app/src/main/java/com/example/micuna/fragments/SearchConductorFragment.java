package com.example.micuna.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.micuna.R;


public class SearchConductorFragment extends Fragment {

    public SearchConductorFragment() {
        // Required empty public constructor
    }
    public static SearchConductorFragment newInstance(String param1, String param2) {
        SearchConductorFragment fragment = new SearchConductorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_conductor, container, false);
    }
}
