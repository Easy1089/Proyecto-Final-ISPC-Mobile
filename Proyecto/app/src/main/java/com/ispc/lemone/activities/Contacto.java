package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ispc.lemone.R;

public class Contacto extends AppCompatActivity {

    private FrameLayout btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacto.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void mensaje (View view) {
       Toast.makeText(this,"Su mensaje ha sido enviado con exito",Toast.LENGTH_LONG).show();
    }
        // mensaje que aparece en 3.5 seg dentro de la misma ventana - fbenitez }


}
