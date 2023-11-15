package com.example.semana12sql_lite.Modelo;

public class Usuario {
    private Integer id;
    private String Nombre;
    private String Correo;

    public Usuario(){

    }
    public Usuario(Integer id, String nombre, String correo) {
        this.id = id;
        Nombre = nombre;
        Correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Correo='" + Correo + '\'' +
                '}';
    }
}
