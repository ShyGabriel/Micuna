package com.example.micuna.include;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.micuna.R;

public class ContenidoToolbar {
    public static void show(AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar_contenido);
        activity.setSupportActionBar(toolbar);
    }
}
