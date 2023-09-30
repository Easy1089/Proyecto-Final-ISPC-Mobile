package com.ispc.lemone;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void cambiar (View View) {

        Intent obj=new Intent(this, Login.class);
        startActivity(obj);
    }
    //Funcion que permite volver atras - fbenitez
}

