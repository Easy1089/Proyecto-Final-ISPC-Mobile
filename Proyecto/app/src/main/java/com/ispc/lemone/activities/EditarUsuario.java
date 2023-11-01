package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Persona;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.Usuario;

public class EditarUsuario extends AppCompatActivity {
    EditText etPassActual;
    EditText etConfirmarPass;
    EditText etNombre;
    EditText etApellido;
    EditText etDatosContacto;
    EditText etTelefono;
    Button buttonGuardar;
    DataBaseHelper dataBaseHelper;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        etPassActual = findViewById(R.id.etPassActual);
        etConfirmarPass = findViewById(R.id.etConfirmarPass);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etDatosContacto = findViewById(R.id.etDatosContacto);
        etTelefono = findViewById(R.id.etTelefono);
        buttonGuardar = findViewById(R.id.btnGuardar);

        dataBaseHelper = new DataBaseHelper(EditarUsuario.this);
        usuario = new Usuario();

        try {
            usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al obtener el usuario en edición", Toast.LENGTH_SHORT).show();
            finish();
        }

        etNombre.setText(usuario.getPersona().getNombre());
        etApellido.setText(usuario.getPersona().getApellido());
        etDatosContacto.setText(usuario.getPersona().getDomicilio());
        etTelefono.setText(usuario.getPersona().getTelefono());

    buttonGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nombrePersona = etNombre.getText().toString();
            String apellidoPersona = etApellido.getText().toString();
            String datoContactoPersona = etDatosContacto.getText().toString();
            String telefonoPersona = etTelefono.getText().toString();
            String passActual = etPassActual.getText().toString();
            String passNueva = etConfirmarPass.getText().toString();

            boolean exito = dataBaseHelper.editarUsuario(usuario,passActual, passNueva, nombrePersona,apellidoPersona,datoContactoPersona, telefonoPersona);

            if (exito){
                Toast.makeText(EditarUsuario.this, "Usuario Editado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
            } else {
                Toast.makeText(EditarUsuario.this, "Error al intentar editar", Toast.LENGTH_SHORT).show();
            }

        }
    });

    }
    public void volver(View view) {
        Intent intent = new Intent(this, BuscarUsuario.class);
        startActivity(intent);
    }
}