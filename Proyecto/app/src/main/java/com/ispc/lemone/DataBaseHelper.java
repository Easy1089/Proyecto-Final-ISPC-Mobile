package com.ispc.lemone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ispc.lemone.activities.AgregarUsuario;
import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.Persona;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.TipoPersona;
import com.ispc.lemone.clases.TipoUsuario;
import com.ispc.lemone.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "lemonemobile_3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String tablaCategorias = "CREATE TABLE IF NOT EXISTS Categorias (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Nombre VARCHAR(50) NOT NULL)";
            db.execSQL(tablaCategorias);

            String tablaProductos = "CREATE TABLE IF NOT EXISTS Productos (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Codigo VARCHAR(20) NOT NULL, " +
                    "Nombre VARCHAR(50) NOT NULL, " +
                    "Descripcion VARCHAR(250) NOT NULL, " +
                    "InventarioMinimo INT, " +
                    "PrecioDeCosto DECIMAL(17, 2), " +
                    "PrecioDeVenta DECIMAL(17, 2), " +
                    "IdCategoria INT, " +
                    "ActivoActualmente BIT, " +
                    "FOREIGN KEY(IdCategoria) REFERENCES Categorias(Id))";
            db.execSQL(tablaProductos);

            String tablaTipoPersonas = "CREATE TABLE IF NOT EXISTS TiposDePersonas (" +
                    "Id INTEGER NOT NULL, " +
                    "Nombre VARCHAR(50) NOT NULL, " +
                    "PRIMARY KEY(Id))";
            db.execSQL(tablaTipoPersonas);

            String tablaPersonas = "CREATE TABLE IF NOT EXISTS Personas (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Apellido VARCHAR(50) NOT NULL, " +
                    "Nombre VARCHAR(50) NOT NULL, " +
                    "Telefono NUMERIC, " +
                    "IdTipoDePersona INT, " +
                    "ActivoActualmente BIT NOT NULL DEFAULT 1, " +
                    "Domicilio VARCHAR(200), " +
                    "FOREIGN KEY(IdTipoDePersona) REFERENCES TiposDePersonas(Id))";
            db.execSQL(tablaPersonas);

            String tablaTiposUsuarios = "CREATE TABLE IF NOT EXISTS TiposDeUsuarios (" +
                    "Id INT NOT NULL, " +
                    "Nombre VARCHAR(50) NOT NULL, " +
                    "PRIMARY KEY(Id))";
            db.execSQL(tablaTiposUsuarios);

            String tablaTiposOperacion = "CREATE TABLE IF NOT EXISTS TiposDeOperacion (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Nombre VARCHAR(50))";
            db.execSQL(tablaTiposOperacion);

            String tablaUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "IdTipoDeUsuario INT NOT NULL, " +
                    "IdPersona INT, " +
                    "Email VARCHAR(50), " +
                    "Password VARCHAR(255) NOT NULL, " +
                    "ActivoActualmente BIT NOT NULL DEFAULT 1, " +
                    "FOREIGN KEY(IdPersona) REFERENCES Personas(Id), " +
                    "FOREIGN KEY(IdTipoDeUsuario) REFERENCES TiposDeUsuarios(Id))";
            db.execSQL(tablaUsuarios);

            String tablaOrdenes = "CREATE TABLE IF NOT EXISTS Ordenes (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Fecha DATE NOT NULL, " +
                    "IdProducto INT NOT NULL, " +
                    "IdPersona INT, " +
                    "Cantidad INT NOT NULL, " +
                    "IdTipoDeOperacion INT NOT NULL, " +
                    "FOREIGN KEY(IdPersona) REFERENCES Personas(Id), " +
                    "FOREIGN KEY(IdProducto) REFERENCES Productos(Id))";
            db.execSQL(tablaOrdenes);

        db.execSQL("INSERT INTO Categorias VALUES (1,'Categoría 1')");
        db.execSQL("INSERT INTO Categorias VALUES (2,'Categoría 2')");
        db.execSQL("INSERT INTO Categorias VALUES (3,'Categoría 3')");

        db.execSQL("INSERT INTO Productos VALUES (1,'420101','Producto 1','Descripción producto 1',10,120,220,1,1)");

        db.execSQL("INSERT INTO TiposDePersonas VALUES (1,'Consumidor final')");
        db.execSQL("INSERT INTO TiposDePersonas VALUES (2,'Proveedor')");

        db.execSQL("INSERT INTO Personas VALUES (1,'Apaz','Melisa',3512553895,1,1,'Domicilio 1')");
        db.execSQL("INSERT INTO Personas VALUES (2,'Perez','Juan',3512553896,2,1,'Domicilio')");
        db.execSQL("INSERT INTO Personas VALUES (3,'Castro','Marta',3512553897,1,1,'Domicilio')");
        db.execSQL("INSERT INTO Personas VALUES (4,'Castillo','Pedro',3512553898,2,1,'Domicilio')");

        db.execSQL("INSERT INTO TiposDeUsuarios VALUES (1,'Administrador')");
        db.execSQL("INSERT INTO TiposDeUsuarios VALUES (2,'Usuario')");

        db.execSQL("INSERT INTO TiposDeOperacion VALUES (1,'Ingreso de stock')");
        db.execSQL("INSERT INTO TiposDeOperacion VALUES (2,'Egreso de stock')");

        db.execSQL("INSERT INTO Usuarios VALUES (1,1,1,'admin@admin.com','12345678',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (2,2,2,'melisaapaz@gmail.com','12345678',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (3,2,3,'juanperez@gmail.com','12345678',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (4,2,4,'martasanchez@gmail.com','12345678',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (5,2,1,'user@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (6,2,2,'user2@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (7,2,3,'user3@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (8,2,4,'user4@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (9,2,1,'user5@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (10,1,2,'admin@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (11,2,3,'admin2@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (12,1,4,'admin3@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (13,2,1,'admin4@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (14,1,2,'admin5@gmail.com','123',1)");
//        db.execSQL("INSERT INTO Usuarios VALUES (15,2,3,'admin6@gmail.com','123',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT * FROM Usuarios";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int idTipoUsuario = cursor.getInt(1);
                int idPersona = cursor.getInt(2);
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("Email"));
                String password = cursor.getString(4);
                boolean activoActualmente = cursor.getInt(5) == 1;

                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setTipoUsuario(buscarTipoUsuarioPorId(idTipoUsuario));
                usuario.setPersona(buscarPersonaPorId(idPersona));
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setActivoActualmente(activoActualmente);

                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return usuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email){
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM Usuarios WHERE email = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            int idTipoUsuario = cursor.getInt(1);
            int idPersona = cursor.getInt(2);
            String password = cursor.getString(4);
            boolean activoActualmente = cursor.getInt(5) == 1;
            usuario.setId(id);
            usuario.setTipoUsuario(buscarTipoUsuarioPorId(idTipoUsuario));
            usuario.setPersona(buscarPersonaPorId(idPersona));
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setActivoActualmente(activoActualmente);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    public Persona buscarPersonaPorId(int id) {
        String query = "SELECT * FROM Personas WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Persona persona = new Persona();
        if (cursor.moveToFirst()) {
            String apellido = cursor.getString(1);
            String nombre = cursor.getString(2);
            double telefono = cursor.getDouble(3);
            int idTipoPersona = cursor.getInt(4);
            String domicilio = cursor.getString(5);
            persona.setId(id);
            persona.setApellido(apellido);
            persona.setNombre(nombre);
            persona.setTelefono(telefono);
            persona.setTipoPersona(buscarTipoPersonaPorId(idTipoPersona));
            persona.setDomicilio(domicilio);
        }
        cursor.close();
        db.close();
        return persona;
    }

    public TipoPersona buscarTipoPersonaPorId (int id){
        String query = "SELECT * FROM TiposDePersonas WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        TipoPersona tipoPersona = null;
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(1);
            tipoPersona = new TipoPersona(id, nombre);
        }
        cursor.close();
        db.close();
        return tipoPersona;
    }

    public TipoUsuario buscarTipoUsuarioPorId(int id) {
        String query = "SELECT * FROM TiposDeUsuarios WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        TipoUsuario tipoUsuario = null;
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(1);
            tipoUsuario = new TipoUsuario(id, nombre);
        }
        cursor.close();
        db.close();
        return tipoUsuario;
    }

    public boolean borrarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Usuarios WHERE id = " + usuario.getId();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
    }

    public boolean guardarUsuario(Usuario usuario) {
        long result = 0;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("IdTipoDeUsuario", usuario.getTipoUsuario().getId());
            values.put("IdPersona", usuario.getPersona().getId());
            values.put("Email", usuario.getEmail());
            values.put("Password", usuario.getPassword());
            values.put("ActivoActualmente", usuario.isActivoActualmente() ? 1 : 0);

            result = db.insert("Usuarios", null, values);
            db.close();
        }catch (Exception ex){
            ex.toString();
        }
        return result != -1;
    }

    public boolean agregarProducto(Producto producto) {
        long result = 0;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Codigo", producto.getCodigo());
            values.put("Nombre", producto.getNombre());
            values.put("Descripcion", producto.getDescripcion());
            values.put("InventarioMinimo", producto.getInventarioMinimo());
            values.put("PrecioDeCosto", producto.getPrecioDeCosto());
            values.put("PrecioDeVenta", producto.getPrecioDeVenta());
            values.put("ActivoActualmente", producto.isActivoActualmente() ? 1 : 0);

            result = db.insert("Productos", null, values);
            db.close();
        }catch (Exception ex){
            ex.toString();
        }
        return result != -1;
    }

    public CategoriaProducto buscarCategoriaPorId (int id){
        String query = "SELECT * FROM Categorias WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CategoriaProducto categoriaProducto = null;
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(1);
            categoriaProducto = new CategoriaProducto(id, nombre);
        }
        cursor.close();
        db.close();
        return categoriaProducto;
    }

    public List<Producto> listaDeProductos() {
        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM Productos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String codigo = cursor.getString(1);
                String nombre = cursor.getString(2);
                String descripcion = cursor.getString(3);
                int inventariominimo = cursor.getInt(4);
                Double preciodecosto = cursor.getDouble(5);
                Double preciodeventa = cursor.getDouble(6);
                int idcategoria = cursor.getInt(7);
                boolean activoActualmente = cursor.getInt(8) == 1;

                Producto producto = new Producto();
                producto.setId(id);
                producto.setCodigo(codigo);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setInventarioMinimo(inventariominimo);
                producto.setPrecioDeCosto(preciodecosto);
                producto.setPrecioDeVenta(preciodeventa);
                producto.setCategoriaProducto(buscarCategoriaPorId(idcategoria));
                producto.setActivoActualmente(activoActualmente);

                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productos;
    }

    public List<Producto> buscarProductosPorCodigo(String codigo) {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos WHERE Codigo = ?";
        String[] selectionArgs = {codigo};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String codigoProducto = cursor.getString(1);
                String nombre = cursor.getString(2);
                String descripcion = cursor.getString(3);

                Producto producto = new Producto();
                producto.setId(id);
                producto.setCodigo(codigoProducto);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);

                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productos;
    }

}
