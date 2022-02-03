package com.mikelalvarez.a4sharedcars.model;

import com.mikelalvarez.a4sharedcars.app.MyAplication;

import java.util.Date;

import io.realm.RealmList;
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

    private double kms;

    private int plazas;

    private Integer conductor;

    private RealmList<Integer> pasajeros;


    public Ruta() {

        this.inicialicePasajeros();
    }

    public Ruta(Date fecha, String hora, String ruta, double kms, int plazas, Integer conductor) {
        this.inicialicePasajeros();
        this.id = MyAplication.idRuta.incrementAndGet();
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
        this.kms = kms;
        this.plazas = plazas;
        this.conductor = conductor;
    }

    public Ruta(int id, Date fecha, String hora, String ruta,double kms, int plazas, Integer conductor, RealmList<Integer> pasajeros) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
        this.kms = kms;
        this.plazas = plazas;
        this.conductor = conductor;
        this.pasajeros = pasajeros;
    }

    private void inicialicePasajeros(){
        this.pasajeros = new RealmList<>();
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

    public Integer getConductor() {
        return conductor;
    }

    public RealmList<Integer> getPasajeros() {
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

    public void setConductor(Integer conductor) {
        this.conductor = conductor;
    }
    public void addPasajero(Integer pasajero){
        this.pasajeros.add(pasajero);
    }

    public void removePasajero(Integer pasajero){
        this.pasajeros.remove(pasajero);
    }
}
