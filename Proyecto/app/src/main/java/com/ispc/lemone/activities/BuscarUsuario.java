package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;

import java.util.ArrayList;
import java.util.List;


public class BuscarUsuario extends AppCompatActivity {

    private Button buttonModificar;
    private Button buttonEliminar3;
    private Button buttonActivar3;
    private Button buttonAgregarUsuario;
    private ListView listViewUsuarios; // ListView para mostrar la lista de usuarios
    private ArrayAdapter<Usuario> adapter;
    private ArrayList<Usuario> listaUsuarios;
    private TextView emailTextView3; // declaro el text view donde se encontraria el email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);

        buttonModificar = findViewById(R.id.buttonModificar3);
        buttonEliminar3 = findViewById(R.id.buttonEliminar3);
        buttonActivar3 = findViewById(R.id.buttonActivar3);
        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);
        listViewUsuarios = findViewById(R.id.listViewUsuarios); // Asocia el ListView de tu layout

        // Inicializa la lista de usuarios y el adaptador
        listaUsuarios = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        listViewUsuarios.setAdapter(adapter);

        // text view del correo
        emailTextView3 = findViewById(R.id.emailTextView3);

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Modificar usuario", "Click en el botón modificar usuario");
                Intent intent = new Intent(BuscarUsuario.this, EditarUsuario.class);
                startActivity(intent);
            }
        });


        buttonEliminar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, EliminarUsuario.class);

                // envio el valor del correo que se encuentra en emailTextView3
                intent.putExtra("email", emailTextView3.getText().toString());

                startActivity(intent);
            }
        });

        buttonActivar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, ActivarDesactivarUsuario.class);
                startActivity(intent);
            }
        });

        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, AgregarUsuario.class);
                startActivity(intent);
            }
        });
        // Cargar la lista de usuarios cuando se inicia la actividad
        cargarUsuarios();
    }

    // Método para cargar y mostrar los usuarios
    private void cargarUsuarios() {
        // Limpiar la lista actual de usuarios
        listaUsuarios.clear();

        // Acceder a la base de datos y cargar los usuarios
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Usuario> usuarios = dbHelper.listarUsuarios();

        if (usuarios != null) {
            listaUsuarios.addAll(usuarios);
        }

        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();
    }

    public void volver(View view) {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

}