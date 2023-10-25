package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;

public class EliminarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);

        Button botonEliminar = findViewById(R.id.btn_eliminar);
        Button botonCancelar = findViewById(R.id.btn_cancelar);

        // traigo los valores del Intent de la vista anterior
        Bundle datosRecibidos = getIntent().getExtras();

        // asigno valor a la variable email
        String email = datosRecibidos.getString("email");

        Usuario usuario =

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
            }
        });
    }
}