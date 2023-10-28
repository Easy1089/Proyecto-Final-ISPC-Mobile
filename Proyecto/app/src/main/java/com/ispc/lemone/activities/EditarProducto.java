package com.ispc.lemone.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;

public class EditarProducto extends AppCompatActivity {

    private FrameLayout btnVolver;
    private Button buttonGuardarEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        buttonGuardarEP = findViewById(R.id.buttonGuardarEP);
        btnVolver = findViewById(R.id.btn_volverEP);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProducto.this,  BuscarProducto.class);
                startActivity(intent);
            }
        });

        try {
            Producto productoSeleccionado = (Producto) getIntent().getSerializableExtra("producto");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EliminarProducto", "Error: " + e.getMessage());
            Toast.makeText(this, "Error al procesar el producto seleccionado", Toast.LENGTH_SHORT).show();
            finish(); // Cierra la actividad en caso de error
        }

        buttonGuardarEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(EditarProducto.this, BuscarProducto.class);
            }
        });
    }
}