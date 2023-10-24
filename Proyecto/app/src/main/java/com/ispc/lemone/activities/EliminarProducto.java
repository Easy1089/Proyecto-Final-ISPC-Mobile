package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ispc.lemone.R;

public class EliminarProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);

        Button botonEliminar = findViewById(R.id.btn_eliminar);
        Button botonCancelar = findViewById(R.id.btn_cancelar);

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });
    }
}