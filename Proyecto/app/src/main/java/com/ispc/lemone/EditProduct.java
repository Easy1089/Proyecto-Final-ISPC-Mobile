package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class EditProduct extends AppCompatActivity {

    private FrameLayout btnVolver;
    private Button buttonGuardarEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        btnVolver = findViewById(R.id.btn_volverEP);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProduct.this, FindProduct.class);
                startActivity(intent);
            }
        });


        buttonGuardarEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(EditProduct.this, FindProduct.class);
            }
        });
    }
}