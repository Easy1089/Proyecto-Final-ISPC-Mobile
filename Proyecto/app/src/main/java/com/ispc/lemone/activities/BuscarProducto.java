package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText editTextCodigoProducto;
    private Button btnBuscarProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        editTextCodigoProducto = findViewById(R.id.editTextText);
        btnVolver = findViewById(R.id.btn_volverFP);
        editar = findViewById(R.id.editarFP);
        activar = findViewById(R.id.activarFP);
        agregarProducto = findViewById(R.id.btn_agregarFP);
        listViewProductos = findViewById(R.id.listViewProductos); // Asocia el ListView de tu layout
        btnBuscarProducto = findViewById(R.id.buttonBuscarFP);

        listaProductos = new ArrayList<>();
        // utilizo un adaptador
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

        btnBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarProductoPorCodigo();
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
        // Notificar al adaptador
        adapter.notifyDataSetChanged();
    }

    // Método para buscar productos por código
    private void buscarProductoPorCodigo() {
        String codigo = editTextCodigoProducto.getText().toString().trim();

        if (!codigo.isEmpty()) {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            // Acceder a la base de datos y cargar los productos
            List<Producto> productosEncontrados = dbHelper.buscarProductosPorCodigo(codigo);
            listaProductos.clear();

            if (productosEncontrados != null && !productosEncontrados.isEmpty()) {
                listaProductos.addAll(productosEncontrados);
            } else {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
            }
            // Notificar al adaptador
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Ingrese un código de producto", Toast.LENGTH_SHORT).show();
        }
    }
}