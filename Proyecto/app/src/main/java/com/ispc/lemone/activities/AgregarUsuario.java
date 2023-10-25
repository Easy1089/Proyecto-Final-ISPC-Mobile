package com.ispc.lemone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.google.firebase.auth.FirebaseAuth;

import com.ispc.lemone.R;

public class AgregarUsuario extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button botonAddUser;
    private FrameLayout btnAtras;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        auth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.textEmail);
        passwordEditText = findViewById(R.id.textContraseña);

        botonAddUser = findViewById(R.id.buttonGuardarAddUser);
        botonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AgregarUsuario.this, task -> {
                                if (task.isSuccessful()) {
                                    // Usuario creado exitosamente
                                    Intent intent = new Intent(AgregarUsuario.this, BuscarUsuario.class);
                                    startActivity(intent);
                                } else {
                                    // Error en la creación del usuario, muestra un mensaje de error
                                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarUsuario.this);
                                    builder.setMessage("Error al crear el usuario: " + task.getException().getMessage())
                                            .setTitle("Error de creación de usuario")
                                            .setPositiveButton("Aceptar", null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            });
                } else {
                    // Asegúrate de que el usuario ingrese tanto el email como la contraseña
                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarUsuario.this);
                    builder.setMessage("Debes ingresar tanto el email como la contraseña.")
                            .setTitle("Error de creación de usuario")
                            .setPositiveButton("Aceptar", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarUsuario.this, ActivarDesactivarUsuario.class);
                startActivity(intent);
            }
        });
    }
}
