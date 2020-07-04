package com.example.micuna.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.micuna.R;
import com.example.micuna.activities.cliente.ContenidoCliente;
import com.example.micuna.activities.cliente.RegistroCliente;
import com.example.micuna.activities.conductor.ContenidoConductor;
import com.example.micuna.activities.conductor.RegistroConductor;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences mPref;
    Button mButtonGoToRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputPassword;
    FirebaseAuth.AuthStateListener authStateListener;
    Button mButtonLogin;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    AlertDialog mDialog;
    CallbackManager callbackManager;
    LoginButton loginButton;
    AccessTokenTracker accessTokenTracker;
    static final String TAG = "FacebookAuthentication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.facebook_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        mButtonGoToRegister = findViewById(R.id.btnGoToRegister);
        mButtonLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);



        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });

        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void goMainScreen(){
            Intent intent = new Intent(LoginActivity.this, ContenidoCliente.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

    }

    private void login() {
        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();
        mDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere un momento").build();

        if (!email.isEmpty() && !password.isEmpty()) {
            if (password.length() >= 6) {
                mDialog.show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "El login se realizó exitosamente", Toast.LENGTH_SHORT).show();
                            String user = mPref.getString("user", "");
                            if (user.equals("client")) {
                                Intent intent = new Intent(LoginActivity.this, ContenidoCliente.class);

                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, ContenidoConductor.class);

                                startActivity(intent);
                            }
                           /* String user = mPref.getString("user", "");
                            if (user.equals("client")) {
                                Intent intent = new Intent(LoginActivity.this, MapClientActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, MapDriverActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }*/
                        } else {
                            Toast.makeText(LoginActivity.this, "La contraseña o el password son incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                Toast.makeText(this, "La contraseña debe tener mas de 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "La contraseña y el email son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToRegister() {

        String typeUser = mPref.getString("user", "");
        if (typeUser.equals("client")) {
            Intent intent = new Intent(LoginActivity.this, RegistroCliente.class);
            startActivity(intent);
        }
        else if(typeUser.equals("driver")){
            Intent intent = new Intent(LoginActivity.this, RegistroConductor.class);
            startActivity(intent);
        }

    }
}
