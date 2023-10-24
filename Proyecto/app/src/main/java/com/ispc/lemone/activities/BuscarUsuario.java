package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ispc.lemone.R;


public class BuscarUsuario extends AppCompatActivity {

    private Button buttonModificar;
    private Button buttonEliminar3;
    private Button buttonActivar3;
    private Button buttonAgregarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);

        buttonModificar = findViewById(R.id.buttonModificar3);
        buttonEliminar3 = findViewById(R.id.buttonEliminar3);
        buttonActivar3 = findViewById(R.id.buttonActivar3);
        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Modificar usuario", "Clic en el botón modificar usuario");
                Intent intent = new Intent(BuscarUsuario.this, EditarUsuario.class);
                startActivity(intent);
            }
        });


        buttonEliminar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, EliminarUsuario.class);
                startActivity(intent);
            }
        });

        buttonActivar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, ActivarDesactivarUsuario.class);
                startActivity(intent);
            }
        });

        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, AgregarUsuario.class);
                startActivity(intent);
            }
        });

    }

    public void volver(View view) {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

}