package com.ispc.lemone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AddConsumer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_final_consumer);

        Button Save = findViewById(R.id.buttonGuardarConsumidor);
        FrameLayout onBack = findViewById(R.id.backACNS);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddConsumer.this, Consumidores_finales.class);
                startActivity(intent);
            }
        });
        onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddConsumer.this, Consumidores_finales.class);
                startActivity(intent);
            }
        });
    }
}
