package com.example.micuna.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.micuna.R;
import com.example.micuna.activities.cliente.RegistroCliente;
import com.example.micuna.activities.conductor.RegistroConductor;
import com.example.micuna.include.MyToolbar;

public class SelectOptionAuth extends AppCompatActivity {
    SharedPreferences mPref;
    Button nButtonGoToLogin;
    Button nButtonGoToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        MyToolbar.show(this,"Seleccionar opcion",true);


        nButtonGoToLogin    = findViewById(R.id.irLogin);
        nButtonGoToRegister = findViewById(R.id.irRegistro);

        nButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });

        nButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });


        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);


    }

    private void goToLogin() {
        Intent intent = new Intent(SelectOptionAuth.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToRegister() {
        String typeUser = mPref.getString("user", "");
        if (typeUser.equals("client")) {
            Intent intent = new Intent(SelectOptionAuth.this, RegistroCliente.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(SelectOptionAuth.this, RegistroConductor.class);
            startActivity(intent);
        }
    }
}
