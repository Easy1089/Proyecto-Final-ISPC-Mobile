package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ispc.lemone.R;

public class AgregarUsuario extends AppCompatActivity {

    private Button botonAddUser;
    private FrameLayout btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        botonAddUser = findViewById(R.id.buttonGuardarAddUser);
        botonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
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
