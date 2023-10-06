package com.ispc.lemone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProducto extends AppCompatActivity {

    private Button buttonGuardarAddProduct;
    private FrameLayout btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        buttonGuardarAddProduct = findViewById(R.id.buttonGuardarAddProduct);
        btnAtras = findViewById(R.id.buttonAtras);
        buttonGuardarAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });
    }
}