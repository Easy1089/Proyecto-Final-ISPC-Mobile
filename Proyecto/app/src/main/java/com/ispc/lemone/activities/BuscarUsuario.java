package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BuscarUsuario extends AppCompatActivity {
    private Button buttonAgregarUsuario;
    private ListView listViewUsuarios;
    private ArrayAdapter<Usuario> adapter;
    private ArrayList<Usuario> listaUsuarios;
    private EditText editTextNombreDeUsuario;
    private Button btnBuscarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);

        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);
        listViewUsuarios = findViewById(R.id.listViewUsuarios);
        btnBuscarUsuario = findViewById(R.id.buttonBuscar);
        editTextNombreDeUsuario = findViewById(R.id.editTextFilter);

        listaUsuarios = new ArrayList<>();
        adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, listaUsuarios) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                return view;
            }
        };
        listViewUsuarios.setAdapter(adapter);

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuarioSeleccionado = listaUsuarios.get(position);
                Intent intent = new Intent(BuscarUsuario.this, EditarUsuario.class);
                intent.putExtra("usuario", usuarioSeleccionado);
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

        btnBuscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsuariosPorNombre();
            }
        });
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        listaUsuarios.clear();
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Usuario> usuarios = dbHelper.listarUsuarios();
        if (usuarios != null) {
            listaUsuarios.addAll(usuarios);
        }
        adapter.notifyDataSetChanged();
    }

    public void volver(View view) {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    private void buscarUsuariosPorNombre() {
        String nombre = editTextNombreDeUsuario.getText().toString().trim();

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Usuario> usuariosencontrados;

        if (nombre.isEmpty()) {
            usuariosencontrados = dbHelper.listarUsuarios();
        } else {
            usuariosencontrados = dbHelper.buscarUsuariosPorNombre(nombre);
        }

        listaUsuarios.clear();

        if (usuariosencontrados != null && !usuariosencontrados.isEmpty()) {
            listaUsuarios.addAll(usuariosencontrados);
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }

}