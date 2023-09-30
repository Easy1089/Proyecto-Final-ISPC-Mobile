package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class ConsultUser extends AppCompatActivity {

    private Button buttonModificar;

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
    }
}