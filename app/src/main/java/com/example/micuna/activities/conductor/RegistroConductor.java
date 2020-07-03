package com.example.micuna.activities.conductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.micuna.R;
import com.example.micuna.activities.cliente.RegistroCliente;
import com.example.micuna.modelo.User;
import com.example.micuna.modelo.Conductor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class RegistroConductor extends AppCompatActivity {

    SharedPreferences mPref;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AlertDialog mDialog;
    // VIEWS
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputPlaca;
    TextInputEditText mTextInputVehiculo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_conductor);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDialog = new SpotsDialog.Builder().setContext(RegistroConductor.this).setMessage("REGISTRANDOSE").build();

        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mTextInputPlaca = findViewById(R.id.textInputPlaca);
        mTextInputVehiculo = findViewById(R.id.textInputVehiculo);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        String selectedUser = mPref.getString("user", "");
        Toast.makeText(this,"El valor que selecciono fue "+ selectedUser, Toast.LENGTH_SHORT).show();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDriver();
            }
        });
    }

    void registerDriver(){
        final String name = mTextInputName.getText().toString();
        final String email = mTextInputEmail.getText().toString();
        final String password = mTextInputPassword.getText().toString();
        final String vehiclePlate = mTextInputPlaca.getText().toString();
        final String vehicleBrand = mTextInputVehiculo.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !vehiclePlate.isEmpty() && !vehicleBrand.isEmpty()){
            if (password.length() >= 6){
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mDialog.hide();
                        if (task.isSuccessful()){
                            String id = mAuth.getCurrentUser().getUid();
                            saveUser(id, name, email, vehiclePlate, vehicleBrand);
                            Toast.makeText(RegistroConductor.this, "Registro de Conductor EXITOSO", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(RegistroConductor.this, "No se puede Registrar el conductor", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void saveUser(String id, String name, String email, String vehicleBrand, String vehiclePlate){
        String selectUser = mPref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setName(vehicleBrand);
        user.setName(vehiclePlate);

        if (selectUser.equals("driver")){
            mDatabase.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistroConductor.this, "Registro Conductor EXITOSO", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistroConductor.this, "Fallo el Registro Conductor", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if (selectUser.equals("client")){
            mDatabase.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistroConductor.this, "Registro Cliente EXITOSO", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistroConductor.this, "Fallo el Registro Cliente", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
