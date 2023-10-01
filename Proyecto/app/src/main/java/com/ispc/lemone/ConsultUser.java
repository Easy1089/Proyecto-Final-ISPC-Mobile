package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class ConsultUser extends AppCompatActivity {

    private Button buttonModificar;
    private Button buttonEliminar3;
    private Button buttonActivar3;
    private Button buttonAgregarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_user);

        buttonModificar = findViewById(R.id.buttonModificar3);
        buttonEliminar3 = findViewById(R.id.buttonEliminar3);
        buttonActivar3 = findViewById(R.id.buttonActivar3);
        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Modificar usuario", "Clic en el botón modificar usuario");
                Intent intent = new Intent(ConsultUser.this, EdidUser.class);
                startActivity(intent);
            }
        });


        buttonEliminar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultUser.this, EliminarUsuario.class);
                startActivity(intent);
            }
        });

        buttonActivar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultUser.this, UserActivarDesactivar.class);
                startActivity(intent);
            }
        });

        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultUser.this, AddUser.class);
                startActivity(intent);
            }
        });

    }

    public void volver(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

}