package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
    }

    public void mensaje (View view) {
        Toast.makeText(this,"Su mensaje ha sido enviado con exito",Toast.LENGTH_LONG).show();
        // mensaje que aparece en 3.5 seg dentro de la misma ventana - fbenitez

    }
}