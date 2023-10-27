package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Persona;

public class EditarUsuario extends AppCompatActivity {
    EditText etPassActual = findViewById(R.id.etPassActual);
    EditText etConfirmarPass = findViewById(R.id.etConfirmarPass);
    EditText etNombre = findViewById(R.id.etNombre);
    EditText etApellido = findViewById(R.id.etApellido);
    EditText etNroDocumento = findViewById(R.id.etNroDocumento);
    EditText etDatosContacto = findViewById(R.id.etDatosContacto);
    EditText etTelefono = findViewById(R.id.etTelefono);

    DataBaseHelper dataBaseHelper;
    Persona persona;

    private Button buttonGuardar = findViewById(R.id.btnGuardar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);


    // Ver si esta ok traer vista anterior

    Bundle personaAModificar = getIntent().getExtras();

// se asigna valor a la variable Id
    String id = personaAModificar.getString("id");

//instanciamiento de la clase db
    dataBaseHelper = new DataBaseHelper(EditarUsuario.this);

    //buscar persona instanciando la consulta
    persona = dataBaseHelper.buscarPersonaPorId(id);

    //traer valores
        //ver contraseñas
        //etPassActual.setText;
        //etConfirmarPass.setText;
        etNombre.setText(persona.getNombre());
        etApellido.setText(persona.getApellido());
        etNroDocumento.setText(String.valueOf(persona.getId()));
        etDatosContacto.setText(persona.getDomicilio());
        etTelefono.setText(String.valueOf(persona.getTelefono()));

//Se modifica boton de guardar instanciando a la query update

    buttonGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataBaseHelper.editarPersona(persona);
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