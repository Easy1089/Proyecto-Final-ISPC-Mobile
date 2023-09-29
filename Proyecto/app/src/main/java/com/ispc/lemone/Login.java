package com.ispc.lemone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private Button botonLogin;
    private EditText usuarioIngresado;
    private EditText passwordIngresado;
    private TextView acercaDe;
    private TextView contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        acercaDe = findViewById(R.id.txt_acerca_de);
        contacto = findViewById(R.id.txt_contacto);

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, AcercaDe.class);
                startActivity(intent);
            }
        });

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Contacto.class);
                startActivity(intent);
            }
        });

        botonLogin = findViewById(R.id.btn_login);
        usuarioIngresado = findViewById(R.id.txt_usuario);
        passwordIngresado = findViewById(R.id.txt_password);


        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
    }
    public void iniciarSesion (){

        String usuario = usuarioIngresado.getText().toString();
        String password = passwordIngresado.getText().toString();

        if (usuario.equals("usuario") && password.equals("123")){
            Intent intent = new Intent(Login.this, MainMenuActivity.class);
            startActivity(intent);
        } else if(usuario.equals("admin") && password.equals("123")){
            Intent intent = new Intent(Login.this, MainMenuActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Credenciales incorrectas. Por favor, inténtalo de nuevo.")
                    .setTitle("Error de inicio de sesión")
                    .setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        usuarioIngresado.setText("");
        passwordIngresado.setText("");
    }

}