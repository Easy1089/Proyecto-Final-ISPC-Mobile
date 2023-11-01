package com.ispc.lemone.clases;

import java.io.Serializable;

public class TipoUsuario implements Serializable {

    private int id;
    private String nombre;

    public TipoUsuario() {
    }

    public TipoUsuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
