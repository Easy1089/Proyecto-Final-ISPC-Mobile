package com.ispc.lemone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private Button buttonUsuarios;
    private Button buttonProveedores;
    private Button buttonConsumidores;
    private Button buttonProductos;
    private Button buttonInforme;
    private Button buttonInformeStock;
    private Button buttonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonUsuarios = findViewById(R.id.buttonUsuarios);
        buttonProveedores = findViewById(R.id.buttonProveedores);;
        buttonConsumidores = findViewById(R.id.buttonConsumidores);
        buttonProductos = findViewById(R.id.buttonProductos);
        buttonInforme = findViewById(R.id.buttonInforme);
        buttonInformeStock = findViewById(R.id.buttonInformeStock);
        buttonSalir = findViewById(R.id.buttonSalir);

        buttonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Usuarios", "Clic en el botón Usuarios");
                Intent intent = new Intent(MainMenuActivity.this, ConsultUser.class);
                startActivity(intent);
            }
        });

        buttonProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Proovedores", "Clic en el botón Proveedores");
                Intent intent = new Intent(MainMenuActivity.this, AddProvider.class);
                startActivity(intent);
            }
        });

        buttonConsumidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Consumidores", "Clic en el botón Consumidores");
                Intent intent = new Intent(MainMenuActivity.this, Consumidores_finales.class);
                startActivity(intent);
            }
        });

        buttonProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Productos", "Clic en el botón Productos");
                Intent intent = new Intent(MainMenuActivity.this, FindProduct.class);
                startActivity(intent);
            }
        });


        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Salir", "Clic en el botón Salir");
                Intent intent = new Intent(MainMenuActivity.this, Login.class);
                startActivity(intent);
            }
        });


    }
}
