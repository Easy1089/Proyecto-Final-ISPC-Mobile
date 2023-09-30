package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FindProduct extends AppCompatActivity {

    private FrameLayout btnVolver;
    private TextView editar;
    private Button agregarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_product);

        btnVolver = findViewById(R.id.btn_volverFP);
        editar = findViewById(R.id.editarFP);
        agregarProducto = findViewById(R.id.btn_agregarFP);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindProduct.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindProduct.this, EditProduct.class);
                startActivity(intent);
            }
        });

        agregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindProduct.this, AddProduct.class);
                startActivity(intent);
            }
        });
    }
}