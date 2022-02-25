package com.mikelalvarez.a4sharedcars.model;

import com.mikelalvarez.a4sharedcars.app.MyAplication;


import java.util.Objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Usuario extends RealmObject {

    @PrimaryKey
    private int id;

    private String nombre;
    private String apellido;
    private String username;
    private String contraseña;
    private String correo;
    private Double puntosC02;
    private Integer puntuacionEstrellas;
    private String telefono;
    private Integer imagen;
    private RealmList<Integer> usuariosFavoritos;
    private RealmList<Integer> usuariosVetados;

    public Usuario() {
        inicializar();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public Usuario(String nombre, String apellido, String username, String contraseña, String correo, String telefono, Integer imagen) {
        this.id = MyAplication.idUsuario.incrementAndGet();
        inicializar();
        this.puntuacionEstrellas = 3;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contraseña = contraseña;
        this.correo = correo;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public Usuario(int id, String nombre, String apellido, String username, String contraseña, String correo, String telefono, Integer imagen) {
        this.id = MyAplication.idUsuario.incrementAndGet();
        inicializar();
        this.puntuacionEstrellas = 3;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contraseña = contraseña;
        this.correo = correo;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public Usuario(int id, String nombre, String apellido, String username, String contraseña, String correo, Double puntosC02, Integer puntuacionEstrellas, String telefono, Integer imagen, RealmList<Integer> usuariosFavoritos, RealmList<Integer> usuariosVetados) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contraseña = contraseña;
        this.correo = correo;
        this.puntosC02 = puntosC02;
        this.puntuacionEstrellas = puntuacionEstrellas;
        this.telefono = telefono;
        this.imagen = imagen;
        this.usuariosFavoritos = usuariosFavoritos;
        this.usuariosVetados = usuariosVetados;
    }
    private void inicializar(){
        this.usuariosFavoritos = new RealmList<Integer>();
        this.usuariosVetados = new RealmList<Integer>();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public RealmList<Integer> getUsuariosFavoritos() {
        return usuariosFavoritos;
    }

    public RealmList<Integer> getUsuariosVetados() {
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

    public Double getPuntosC02() {
        return puntosC02;
    }

    public void setPuntosC02(Double puntosC02) {
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
    public void addFavorito(Integer favorito){
        this.usuariosFavoritos.add(favorito);
    }
    public void removeFavorito(Usuario favorito){
        this.usuariosFavoritos.remove(favorito);
    }
    public void addVetado(Integer vetado){
        this.usuariosVetados.add(vetado);
    }
    public void removeVetado(Usuario vetado){
        this.usuariosVetados.remove(vetado);
    }
}
