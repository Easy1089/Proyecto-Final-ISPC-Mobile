package com.ispc.lemone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;

public class AgregarProducto extends AppCompatActivity {

    private Button buttonGuardarAddProduct;
    private FrameLayout btnAtras;
    private EditText etCodigo;
    private EditText etNombreProducto;
    private EditText etPrecioCosto;
    private EditText etPrecioVenta;
    private EditText etDescripcionProducto;
    private EditText etInventarioMinimo;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        etCodigo = findViewById(R.id.textCodigo);
        etNombreProducto = findViewById(R.id.textNombre);
        etPrecioCosto = findViewById(R.id.textPrecioCosto);
        etPrecioVenta = findViewById(R.id.textPrecioVenta);
        etDescripcionProducto = findViewById(R.id.textDescripcion);
        etInventarioMinimo = findViewById(R.id.textInventario);
        dataBaseHelper = new DataBaseHelper(this);

        buttonGuardarAddProduct = findViewById(R.id.buttonGuardarAddProduct);
        btnAtras = findViewById(R.id.buttonAtras);

        buttonGuardarAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos ingresados por el usuario
                String codigo = etCodigo.getText().toString();
                String nombre = etNombreProducto.getText().toString();
                double preciodecosto = Double.parseDouble(etPrecioCosto.getText().toString());
                double preciodeventa = Double.parseDouble(etPrecioVenta.getText().toString());
                String descripcion = etDescripcionProducto.getText().toString();
                int inventariominimo = Integer.parseInt(etInventarioMinimo.getText().toString());

                // Crear un objeto Producto
                Producto producto = new Producto();
                producto.setCodigo(codigo);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setInventarioMinimo(inventariominimo);
                producto.setPrecioDeCosto(preciodecosto);
                producto.setPrecioDeVenta(preciodeventa);

                // Guardar el producto en la base de datos
                boolean exito = dataBaseHelper.agregarProducto(producto);

                if (exito) {
                    Toast.makeText(AgregarProducto.this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
                    // Redirección a búsqueda de productos
                } else {
                    Toast.makeText(AgregarProducto.this, "Error al agregar el producto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });
    }
}