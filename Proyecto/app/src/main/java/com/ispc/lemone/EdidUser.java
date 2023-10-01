package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class EdidUser extends AppCompatActivity {

    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edid_user);

    buttonGuardar = findViewById(R.id.buttonGuardar);

    buttonGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(EdidUser.this, ConsultUser.class);
            startActivity(intent);
        }
    });


    }

    public void volver(View view) {
        Intent intent = new Intent(this, ConsultUser.class);
        startActivity(intent);
    }
}