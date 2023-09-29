package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EliminarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarusuario);

        Button botonEliminar = findViewById(R.id.btn_eliminar);
        Button botonCancelar = findViewById(R.id.btn_cancelar);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarUsuario.this, UserActivarDesactivar.class);
                startActivity(intent);
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarUsuario.this, ConsultUser.class);
                startActivity(intent);
            }
        });

    }
}