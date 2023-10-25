package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ispc.lemone.R;

public class EditarUsuario extends AppCompatActivity {

    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

    buttonGuardar = findViewById(R.id.btnGuardar);

    buttonGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(EditarUsuario.this, BuscarUsuario.class);
            startActivity(intent);
        }
    });



    }

    public void volver(View view) {
        Intent intent = new Intent(this, BuscarUsuario.class);
        startActivity(intent);
    }
}