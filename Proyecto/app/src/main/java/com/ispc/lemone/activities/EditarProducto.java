package com.ispc.lemone.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.Producto;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditarProducto extends AppCompatActivity {

    private FrameLayout btnVolver;
    private Button buttonGuardarEP;
    private Button buttonEliminar;
    private EditText etCodigo;
    private EditText etNombre;
    private EditText etDescripcion;
    private EditText etInventarioMinimo;
    private EditText etPrecioDeCosto;
    private EditText etPrecioDeVenta;
    private Switch etActivoActualmente;
    private Spinner spinnerCategorias;
    private ArrayAdapter<CategoriaProducto> categoriaAdapter;
    private ArrayList<CategoriaProducto> listaDeCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);
        buttonEliminar = findViewById(R.id.btnEliminar);
        buttonGuardarEP = findViewById(R.id.buttonGuardarEP);
        btnVolver = findViewById(R.id.btn_volverEP);
        spinnerCategorias = findViewById(R.id.spinnerCategorias2);
        etActivoActualmente = findViewById(R.id.swActivo2);

        listaDeCategorias = new ArrayList<>();
        // Llena la lista de categorías
        listaDeCategorias.add(new CategoriaProducto(-1, "Categoría"));
        listaDeCategorias.add(new CategoriaProducto(1, "Vino tinto"));
        listaDeCategorias.add(new CategoriaProducto(2, "Vino blanco"));
        listaDeCategorias.add(new CategoriaProducto(3, "Vino rosado"));

        categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDeCategorias);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(categoriaAdapter);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProducto.this,  BuscarProducto.class);
                startActivity(intent);
            }
        });

        Producto productoEnEdicion = new Producto();

        try {
            productoEnEdicion = (Producto) getIntent().getSerializableExtra("producto");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Producto", "Error: " + e.getMessage());
            Toast.makeText(this, "Error al obtener el producto en edición", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Vincular los elementos de la interfaz de productos con las propiedades del objeto Producto
        EditText etCodigo = findViewById(R.id.textCodigo);
        EditText etNombre = findViewById(R.id.textNombre);
        EditText etDescripcion = findViewById(R.id.textDescripcion);
        EditText etInventarioMinimo = findViewById(R.id.textInventario);
        EditText etPrecioDeCosto = findViewById(R.id.textPrecioCosto);
        EditText etPrecioDeVenta = findViewById(R.id.textPrecioVenta);

        // Llenar los elementos de la interfaz de productos con los datos del producto
        etCodigo.setText(productoEnEdicion.getCodigo());
        etNombre.setText(productoEnEdicion.getNombre());
        etDescripcion.setText(productoEnEdicion.getDescripcion());
        etInventarioMinimo.setText(String.valueOf(productoEnEdicion.getInventarioMinimo()));
        double precioDeCosto = productoEnEdicion.getPrecioDeCosto();
        etPrecioDeCosto.setText(Double.toString(precioDeCosto));
        double precioDeVenta = productoEnEdicion.getPrecioDeVenta();
        etPrecioDeVenta.setText(Double.toString(precioDeVenta));
        boolean estadoSwitch = productoEnEdicion.isActivoActualmente();
        etActivoActualmente.setChecked(estadoSwitch);
        buttonGuardarEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(EditarProducto.this, BuscarProducto.class);
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarProducto.this, EliminarProducto.class);
                Producto productoSeleccionado = (Producto) getIntent().getSerializableExtra("producto");
                intent.putExtra("producto", productoSeleccionado);
                startActivity(intent);
            }
        });
    }
}