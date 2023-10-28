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
    private Button buttonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);
        buttonEliminar = findViewById(R.id.btnEliminar);
        buttonGuardarEP = findViewById(R.id.buttonGuardarEP);
        btnVolver = findViewById(R.id.btn_volverEP);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProducto.this,  BuscarProducto.class);
                startActivity(intent);
            }
        });

        //OBTENCIÓN DEL PRODUCTO QUE VIENE DE LA ACTIVITY ANTERIOR!!!!
        try {
            Producto productoSeleccionado = (Producto) getIntent().getSerializableExtra("producto");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Producto", "Error: " + e.getMessage());
            Toast.makeText(this, "Error al obtener el producto seleccionado", Toast.LENGTH_SHORT).show();
            finish(); //
        }

        buttonGuardarEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(EditarProducto.this, BuscarProducto.class);
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProducto.this, EliminarProducto.class);
                Producto productoSeleccionado = (Producto) getIntent().getSerializableExtra("producto");
                intent.putExtra("producto", productoSeleccionado);
                startActivity(intent);
            }
        });
    }
}