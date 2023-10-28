package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ispc.lemone.R;
import com.ispc.lemone.DataBaseHelper; // Importa la clase de tu DataBaseHelper
import com.ispc.lemone.clases.Producto;

public class EliminarProducto extends AppCompatActivity {

    private FrameLayout btnVolver;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);

        dbHelper = new DataBaseHelper(this);

        Button botonEliminar = findViewById(R.id.btn_eliminar);
        Button botonCancelar = findViewById(R.id.btn_cancelar);
        TextView productNameTextView = findViewById(R.id.productName);
        TextView productPriceTextView = findViewById(R.id.productPrice);
        TextView productCatTextView = findViewById(R.id.productCat);
        TextView productCodeTextView = findViewById(R.id.productCode);

        Producto producto = (Producto) getIntent().getSerializableExtra("producto");

        // Asigna los datos del producto a los TextView correspondientes
        productNameTextView.setText(producto.getNombre());
        productPriceTextView.setText(String.valueOf(producto.getPrecioDeVenta()));
        productCatTextView.setText((CharSequence) producto.getCategoriaProducto());
        productCodeTextView.setText(producto.getCodigo());

        btnVolver = findViewById(R.id.btn_volverFP);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtén el ID del producto desde el objeto Producto
                int productoIdAEliminar = producto.getId();

                boolean eliminado = dbHelper.eliminarProductoPorId(productoIdAEliminar);

                if (eliminado) {
                    Toast.makeText(EliminarProducto.this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
                    // Puedes redirigir o realizar otras acciones aquí
                } else {
                    Toast.makeText(EliminarProducto.this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show();
                    // Puedes mostrar un mensaje de error o realizar otra acción aquí
                }
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });
    }
}
