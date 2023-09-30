package com.ispc.lemone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

    }

    public void resetpassword (View view) {
        Toast.makeText(this,"Se ha enviado un e-mail con las intrucciones para restablecer su cuenta.",Toast.LENGTH_LONG).show();
    }

    public void volverAlLoguin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}