package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ConsultUser extends AppCompatActivity {

    private Button buttonModificar;
    private Button buttonEliminar;
    private Button buttonActivar;
    private Button btnActivarA;
    private Button botonAddUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_user);

        buttonModificar = findViewById(R.id.buttonModificar3);

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Modificar usuario", "Clic en el botón modificar usuario");
                Intent intent = new Intent(ConsultUser.this, EdidUser.class);
                startActivity(intent);
            }
        });

        buttonEliminar = findViewById(R.id.buttonEliminar3);

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón eliminar usuario", "Clic en el botón eliminar usuario");
                Intent intent = new Intent(ConsultUser.this, EliminarUsuario.class);
                startActivity(intent);
            }
        });

        btnActivarA = findViewById(R.id.buttonActivar3);
        btnActivarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ConsultUser.this, UserActivarDesactivar.class);
                startActivity(intent);
            }
        });

        /*botonAddUser = findViewById(R.id.buttonGuardar2);
        botonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultUser.this, AddUser.class);
                startActivity(intent);
            }
        });*/
    }

    public void activardesactivarusuario (View view) {
        Toast.makeText(this,"Se ha activado/desactivado el usuario seleccionado.",Toast.LENGTH_LONG).show();
    }
    public void volver(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}