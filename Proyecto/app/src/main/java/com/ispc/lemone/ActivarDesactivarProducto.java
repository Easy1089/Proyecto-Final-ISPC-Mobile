package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivarDesactivarProducto extends AppCompatActivity {

    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_desactivar_producto);

        cancelar = findViewById(R.id.btn_cancelarADP);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivarDesactivarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });
    }
}