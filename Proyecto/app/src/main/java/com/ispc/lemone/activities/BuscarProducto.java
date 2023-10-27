package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ispc.lemone.R;

public class BuscarProducto extends AppCompatActivity {

    private FrameLayout btnVolver;
    private TextView editar;
    private TextView activar;
    private Button agregarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        btnVolver = findViewById(R.id.btn_volverFP);
        editar = findViewById(R.id.editarFP);
        activar = findViewById(R.id.activarFP);
        agregarProducto = findViewById(R.id.btn_agregarFP);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, EditarProducto.class);
                startActivity(intent);
            }
        });

        activar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, ActivarDesactivarProducto.class);
                startActivity(intent);
            }
        });

        agregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, AgregarProducto.class);
                startActivity(intent);
            }
        });
    }
}