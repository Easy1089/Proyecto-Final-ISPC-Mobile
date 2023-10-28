package com.ispc.lemone.clases;

import java.util.Date;

public class Orden {

    private int id;
    private Date fecha;
    private Producto producto;
    private Persona persona;
    private int cantidad;
    private TipoOperacion tipoOperacion;

    public Orden() {
    }

    public Orden(int id, Date fecha, Producto producto, Persona persona, int cantidad, TipoOperacion tipoOperacion) {
        this.id = id;
        this.fecha = fecha;
        this.producto = producto;
        this.persona = persona;
        this.cantidad = cantidad;
        this.tipoOperacion = tipoOperacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
}
