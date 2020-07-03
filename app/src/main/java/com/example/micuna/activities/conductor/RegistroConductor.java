package com.example.micuna.activities.conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.micuna.R;

public class RegistroConductor extends AppCompatActivity {
    SharedPreferences mPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_conductor);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        String selectedUser = mPref.getString("user","");
        Toast.makeText(this, "El valor que seleccionó fue " + selectedUser, Toast.LENGTH_SHORT).show();
    }
}
