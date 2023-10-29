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
    private EditText etNombre;
    private EditText etEmail;
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

    public void mensaje(View view) {
        if (validarCampos()) {
            // Los campos son válidos, puedes procesar los datos aquí
            Toast.makeText(this, "Su mensaje ha sido enviado con éxito", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarCampos() {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombre.setError("Este campo es requerido");
            return false;
        }

        if (!isValidEmail(email)) {
            etEmail.setError("Correo electrónico no válido");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    //public void mensaje (View view) {
      //  Toast.makeText(this,"Su mensaje ha sido enviado con exito",Toast.LENGTH_LONG).show();
    //}
        // mensaje que aparece en 3.5 seg dentro de la misma ventana - fbenitez }


}
