package com.mikelalvarez.a4sharedcars.model;

import com.mikelalvarez.a4sharedcars.app.MyAplication;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Usuario extends RealmObject {

    @PrimaryKey
    private int id;

    private String nombre;
    private String apellido;
    private String username;
    private String contraseña;
    private Float puntosC02;
    private Integer puntuacionEstrellas;
    private String telefono;
    private Integer imagen;
    private ArrayList<Usuario> usuariosFavoritos;
    private ArrayList<Usuario> usuariosVetados;

    public Usuario() {
        inicializar();
    }

    public Usuario(String nombre, String apellido, String username, String contraseña, String telefono, Integer imagen) {
        this.id = MyAplication.idUsuario.incrementAndGet();
        inicializar();
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public Usuario(int id, String nombre, String apellido, String username, String contraseña, Float puntosC02, Integer puntuacionEstrellas, String telefono, Integer imagen, ArrayList<Usuario> usuariosFavoritos, ArrayList<Usuario> usuariosVetados) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contraseña = contraseña;
        this.puntosC02 = puntosC02;
        this.puntuacionEstrellas = puntuacionEstrellas;
        this.telefono = telefono;
        this.imagen = imagen;
        this.usuariosFavoritos = usuariosFavoritos;
        this.usuariosVetados = usuariosVetados;
    }
    private void inicializar(){
        this.usuariosFavoritos = new ArrayList<Usuario>();
        this.usuariosVetados = new ArrayList<Usuario>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Usuario> getUsuariosFavoritos() {
        return usuariosFavoritos;
    }

    public ArrayList<Usuario> getUsuariosVetados() {
        return usuariosVetados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Float getPuntosC02() {
        return puntosC02;
    }

    public void setPuntosC02(Float puntosC02) {
        this.puntosC02 = puntosC02;
    }

    public Integer getPuntuacionEstrellas() {
        return puntuacionEstrellas;
    }

    public void setPuntuacionEstrellas(Integer puntuacionEstrellas) {
        this.puntuacionEstrellas = puntuacionEstrellas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
    public void addFavorito(Usuario favorito){
        this.usuariosFavoritos.add(favorito);
    }
    public void removeFavorito(Usuario favorito){
        this.usuariosFavoritos.remove(favorito);
    }
    public void addVetado(Usuario vetado){
        this.usuariosVetados.add(vetado);
    }
    public void removeVetado(Usuario vetado){
        this.usuariosVetados.remove(vetado);
    }
}
