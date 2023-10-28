package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.adapters.ProductoAdapter;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BuscarProducto extends AppCompatActivity {

    private FrameLayout btnVolver;
    private TextView editar;
    private TextView activar;
    private Button agregarProducto;
    private ArrayAdapter<Producto> adapter;
    private ArrayList<Producto> listaProductos;
    private ListView listViewProductos; // ListView para mostrar la lista de productos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        btnVolver = findViewById(R.id.btn_volverFP);
        editar = findViewById(R.id.editarFP);
        activar = findViewById(R.id.activarFP);
        agregarProducto = findViewById(R.id.btn_agregarFP);
        listViewProductos = findViewById(R.id.listViewProductos); // Asocia el ListView de tu layout

        // Inicializa la lista de productos y el adaptador
        listaProductos = new ArrayList<>();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProductos);
        //listViewProductos.setAdapter(adapter);

        // con estas líneas
        adapter = new ProductoAdapter(this, listaProductos);
        listViewProductos.setAdapter(adapter);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, EditarProducto.class);
                startActivity(intent);
            }
        });

        activar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, ActivarDesactivarProducto.class);
                startActivity(intent);
            }
        });

        agregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, AgregarProducto.class);
                startActivity(intent);
            }
        });

        // Cargar la lista de productos cuando se inicia la actividad
        cargarProductos();
    }

    // Método para cargar y mostrar los productos
    private void cargarProductos() {
        // Limpiar la lista actual de productos
        listaProductos.clear();

        // Acceder a la base de datos y cargar los productos
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Producto> productos = dbHelper.listaDeProductos();

        if (productos != null) {
            listaProductos.addAll(productos);
        }
        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();
    }
}