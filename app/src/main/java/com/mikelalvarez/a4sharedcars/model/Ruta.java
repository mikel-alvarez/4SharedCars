package com.mikelalvarez.a4sharedcars.model;

import com.mikelalvarez.a4sharedcars.app.MyAplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Ruta extends RealmObject {

    @PrimaryKey
    private int id;

    private Date fecha;

    private String hora;
    @Required
    private String ruta;

    private int plazas;

    private Usuario conductor;

    private ArrayList<Usuario> pasajeros;


    public Ruta() {

        this.inicialicePasajeros();
    }

    public Ruta(Date fecha, String hora, String ruta, int plazas, Usuario conductor) {
        this.inicialicePasajeros();
        this.id = MyAplication.idRuta.incrementAndGet();
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
        this.plazas = plazas;
        this.conductor = conductor;
    }

    public Ruta(int id, Date fecha, String hora, String ruta, int plazas, Usuario conductor, ArrayList<Usuario> pasajeros) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
        this.plazas = plazas;
        this.conductor = conductor;
        this.pasajeros = pasajeros;
    }

    private void inicialicePasajeros(){
        this.pasajeros = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getRuta() {
        return ruta;
    }

    public int getPlazas() {
        return plazas;
    }

    public Usuario getConductor() {
        return conductor;
    }

    public ArrayList<Usuario> getPasajeros() {
        return pasajeros;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public void setConductor(Usuario conductor) {
        this.conductor = conductor;
    }
    public void addPasajero(Usuario pasajero){
        this.pasajeros.add(pasajero);
    }

    public void removePasajero(Usuario pasajero){
        this.pasajeros.remove(pasajero);
    }
}
