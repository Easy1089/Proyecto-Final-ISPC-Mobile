package com.ispc.lemone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AddProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

         Button Save = findViewById(R.id.buttonGuardarConsumidor);
         FrameLayout onBack = findViewById(R.id.backAPVD);
          Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProvider.this, Proveed.class);
                startActivity(intent);
            }
        });
          onBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent intent = new Intent(AddProvider.this, Proveed.class);
                startActivity(intent);
        }
    });

    }
}
