package com.ispc.lemone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private Context context;
    private ArrayList<Producto> productos;

    public ProductoAdapter(Context context, ArrayList<Producto> productos) {
        super(context, 0, productos);
        this.context = context;
        this.productos = productos;
    }

   // @Override
   // public View getView(int position, View convertView, ViewGroup parent) {
      //  if (convertView == null) {
      //      convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
      //  }
      //  Producto producto = productos.get(position);
      //  TextView nombreProducto = convertView.findViewById(R.id.textViewNombreProducto);
      //  nombreProducto.setText(producto.getNombre());

     //   return convertView;
    //}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, parent, false);
        }
        Producto producto = getItem(position);
        TextView textViewProducto = convertView.findViewById(R.id.textViewProducto);
        String codigoYNombre = "(" + producto.getCodigo() + ") - " + producto.getNombre();
        textViewProducto.setText(codigoYNombre);
        return convertView;

    }

}
