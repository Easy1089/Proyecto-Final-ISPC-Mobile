package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivarDesactivar extends AppCompatActivity {

    Button btn_act_usuario;
    Button btn_cancel_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadousuario);

        btn_cancel_user=findViewById(R.id.btn_cancelar_user);

        btn_cancel_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivarDesactivar.this, ConsultUser.class);
            }
        });

        btn_act_usuario = findViewById(R.id.act_desact_usuario);
        btn_act_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivarDesactivar.this, AddUser.class);
                startActivity(intent);
            }


        });
    }
}