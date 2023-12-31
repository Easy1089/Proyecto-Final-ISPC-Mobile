package com.ispc.lemone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ispc.lemone.activities.AgregarUsuario;
import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.InventarioMinimoPorProducto;
import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.Persona;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.TipoPersona;
import com.ispc.lemone.clases.TipoUsuario;
import com.ispc.lemone.clases.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        db.execSQL("INSERT INTO Usuarios VALUES (5,2,1,'user@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (6,2,2,'user2@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (7,2,3,'user3@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (8,2,4,'user4@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (9,2,1,'user5@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (10,1,2,'admin@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (11,2,3,'admin2@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (12,1,4,'admin3@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (13,2,1,'admin4@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (14,1,2,'admin5@gmail.com','123',1)");
        db.execSQL("INSERT INTO Usuarios VALUES (15,2,3,'admin6@gmail.com','123',1)");


        // Insertar órdenes utilizando consultas SQL
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('23/10/2023', 1, 1, 1, 10)");
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('24/10/2023', 1, 1, 2, 2)");
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('25/10/2023', 1, 2, 1, 10)");
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('26/10/2023', 1, 2, 2, 3)");
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

    public Usuario buscarUsuarioPorEmail(String email) {
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

    public TipoPersona buscarTipoPersonaPorId(int id) {
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

    public boolean borrarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Usuarios WHERE id = " + usuario.getId();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
    }




    public boolean editarUsuario(Usuario usuario, String etPassActual, String etConfirmarPass, String etNombre, String etApellido, String etDatosContacto, double etTelefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idPersona = usuario.getPersona().getId();
        int idUsuario = usuario.getId();
        int filasActualizadasPersona = 0;
        int filasActualizadasTablaUsuario = 0;

        db.beginTransaction();

        try {


            // Se actualiza la otra tabla solo si antiguoPassword coincide con el valor existente
            filasActualizadasTablaUsuario = 0;
            if (etConfirmarPass != null && etPassActual != null) {
                // Se compara nuevoPassword con el valor existente en la base de datos
                Cursor cursor = db.rawQuery("SELECT password FROM Usuario WHERE id = " + idUsuario, null);
                if (cursor.moveToFirst()) {
                    String passwordExistente = cursor.getString(0);
                    if (passwordExistente.equals(etPassActual)) {
                        ContentValues valuesTablaRelacionada = new ContentValues();
                        valuesTablaRelacionada.put("password", etConfirmarPass);
                        filasActualizadasTablaUsuario = db.update("Usuario", valuesTablaRelacionada, "id = " + idUsuario, null);

                        ContentValues valuesPersona = new ContentValues();
                        valuesPersona.put("nombre", etNombre);
                        valuesPersona.put("apellido", etApellido);
                        valuesPersona.put("domicilio", etDatosContacto);
                        valuesPersona.put("telefono", etTelefono);

                        // Se actualiza la tabla Personas
                        filasActualizadasPersona = db.update("Personas", valuesPersona, "id = " + idPersona, null);
                    }
                }
                cursor.close();
            }
            // Comprobamos que al menos una de las actualizaciones tuvo éxito
            if (filasActualizadasPersona > 0 || filasActualizadasTablaUsuario > 0) {
                // Confirmación de la transacción
                db.setTransactionSuccessful();
                return true;
            } else {
                // Revertimos la transacción en caso de error
                return false;
            }
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public boolean guardarUsuario(Usuario usuario) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("IdTipoDeUsuario", 2);
            //values.put("IdPersona", 1);
            values.put("Email", usuario.getEmail());
            values.put("Password", usuario.getPassword());
            values.put("ActivoActualmente", 1);

            result = db.insert("Usuarios", null, values);
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return result != -1;
    }


    public boolean agregarProducto(Producto producto) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Codigo", producto.getCodigo());
            values.put("Nombre", producto.getNombre());
            values.put("Descripcion", producto.getDescripcion());
            values.put("InventarioMinimo", producto.getInventarioMinimo());
            values.put("PrecioDeCosto", producto.getPrecioDeCosto());
            values.put("PrecioDeVenta", producto.getPrecioDeVenta());
            values.put("IdCategoria", producto.getCategoriaProducto().getId());
            values.put("ActivoActualmente", producto.isActivoActualmente() ? 1 : 0);

            result = db.insert("Productos", null, values);
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return result != -1;
    }

    public CategoriaProducto buscarCategoriaPorId(int id) {
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
                int idcategoria = 1;//cursor.getInt(7);
                int activoActualmenteInt = cursor.getInt(8);
                boolean activoActualmente = (activoActualmenteInt == 1);

                Producto producto = new Producto();
                producto.setId(id);
                producto.setCodigo(codigo);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setInventarioMinimo(inventariominimo);
                producto.setPrecioDeCosto(preciodecosto);
                producto.setPrecioDeVenta(preciodeventa);
                //producto.setCategoriaProducto(buscarCategoriaPorId(idcategoria));
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

    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        String query;
        String[] selectionArgs;

        if (!nombre.isEmpty()) {
            query = "SELECT * FROM Usuarios WHERE Email LIKE ?";
            selectionArgs = new String[]{"%" + nombre + "%"};
        } else {
            query = "SELECT * FROM Usuarios";
            selectionArgs = null;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectionArgs);

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

            cursor.close();
            db.close();

            return usuarios;
        }
        return usuarios;
    }

    public boolean eliminarProductoPorId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Productos WHERE Id = " + id;

        try {
            db.execSQL(query);
            db.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
            return false;
        }
    }

    public boolean actualizarProducto(Producto productoSeleccionado) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Codigo", productoSeleccionado.getCodigo());
            values.put("Nombre", productoSeleccionado.getNombre());
            values.put("Descripcion", productoSeleccionado.getDescripcion());
            values.put("InventarioMinimo", productoSeleccionado.getInventarioMinimo());
            values.put("PrecioDeCosto", productoSeleccionado.getPrecioDeCosto());
            values.put("PrecioDeVenta", productoSeleccionado.getPrecioDeVenta());
            values.put("IdCategoria", productoSeleccionado.getCategoriaProducto().getId());
            values.put("ActivoActualmente", productoSeleccionado.isActivoActualmente() ? 1 : 0);

            result = db.update("Productos", values, "ID=?", new String[]{String.valueOf(productoSeleccionado.getId())});
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return result != 0;
    }

    public List<Orden> getOrdenesConDetalles() {
        List<Orden> ordenes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT o.Id, strftime('%d/%m/%Y', DATE(SUBSTR(o.Fecha, 7, 4) || '-' || SUBSTR(o.Fecha, 4, 2) || '-' || SUBSTR(o.Fecha, 1, 2))) as Fecha, pro.Codigo, pro.Nombre as Producto, o.Cantidad, t.Nombre as TipoDeOperacion, (p.Apellido || ', ' || p.Nombre) as Persona FROM Ordenes o inner join Personas p on p.Id = o.IdPersona inner join Productos pro on pro.Id = o.IdProducto inner join TiposDeOperacion t on t.Id = o.IdTipoDeOperacion";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String fecha = cursor.getString(cursor.getColumnIndex("Fecha"));
                @SuppressLint("Range") String codigoProducto = cursor.getString(cursor.getColumnIndex("Codigo"));
                @SuppressLint("Range") String nombreProducto = cursor.getString(cursor.getColumnIndex("Producto"));
                int cantidad = cursor.getInt(cursor.getInt(3));
                @SuppressLint("Range") String tipoOperacion = cursor.getString(cursor.getColumnIndex("TipoDeOperacion"));
                @SuppressLint("Range") String nombrePersona = cursor.getString(cursor.getColumnIndex("Persona"));

                Orden orden = new Orden();
                orden.setFecha(fecha);
                orden.setCodigoProducto(codigoProducto);
                orden.setNombreProducto(nombreProducto);
                orden.setCantidad(cantidad);
                orden.setTipoOperacion(tipoOperacion);
                orden.setNombrePersona(nombrePersona);

                ordenes.add(orden);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ordenes;
    }

    public List<Producto> getInventario() {
        List<Producto> inventarios = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT p.Id, P.Codigo, P.Nombre As Producto, C.Nombre as Categoria, P.InventarioMinimo FROM Productos P INNER JOIN Categorias C ON C.Id = P.IdCategoria WHERE P.ActivoActualmente = 1 AND P.InventarioMinimo < 100";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(cursor.getInt(0));
                //@SuppressLint("Range") String codigoProducto = cursor.getString(cursor.getColumnIndex("Codigo"));
                //@SuppressLint("Range") String nombreProducto = cursor.getString(cursor.getColumnIndex("Producto"));
                //@SuppressLint("Range")  String categoria = cursor.getString(cursor.getColumnIndex("Categoria"));
                //int inventarioInt = cursor.getInt(cursor.getInt(4));

                Producto inventario = new Producto();

                inventario.setId(id);
                //inventario.setCodigo(codigoProducto);
                //inventario.setNombre(nombreProducto);
                //inventario.setCategoriaDeProducto(categoria);
                //inventario.setInventarioMinimo(inventarioInt);

                inventarios.add(inventario);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return inventarios;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

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

