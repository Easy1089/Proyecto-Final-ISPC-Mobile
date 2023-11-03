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
import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.activities.MenuPrincipal;


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
    DataBaseHelper dataBaseHelper;
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
                Intent intent = new Intent(EditarProducto.this, MenuPrincipal.class);
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

        final Producto finalProductoEnEdicion = productoEnEdicion;
        dataBaseHelper = new DataBaseHelper(this);


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
                // Obtén los valores de los campos de entrada
                String codigo = etCodigo.getText().toString();
                String nombre = etNombre.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                String inventarioMinimoStr = etInventarioMinimo.getText().toString();
                String precioDeCostoStr = etPrecioDeCosto.getText().toString();
                String precioDeVentaStr = etPrecioDeVenta.getText().toString();
                boolean estadoSwitch = etActivoActualmente.isChecked();
                CategoriaProducto categoriaSeleccionada = (CategoriaProducto) spinnerCategorias.getSelectedItem();

                // Convierte los valores de cadena a los tipos de datos necesarios
                int inventarioMinimo = Integer.parseInt(inventarioMinimoStr);
                double precioDeCosto = Double.parseDouble(precioDeCostoStr);
                double precioDeVenta = Double.parseDouble(precioDeVentaStr);

                // Obtén el ID del producto en edición
                int productoId = finalProductoEnEdicion.getId();

                // Crea un objeto Producto actualizado con los valores
                Producto productoActualizado = new Producto(productoId, codigo, nombre, descripcion, inventarioMinimo, precioDeCosto, precioDeVenta, categoriaSeleccionada, estadoSwitch);

                // Llama al método para editar el producto en la base de datos

                boolean exito = dataBaseHelper.actualizarProducto(productoActualizado);

                if (exito) {
                    Toast.makeText(EditarProducto.this, "Producto Editado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditarProducto.this, BuscarProducto.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditarProducto.this, "Error al intentar editar el producto", Toast.LENGTH_SHORT).show();
                }
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