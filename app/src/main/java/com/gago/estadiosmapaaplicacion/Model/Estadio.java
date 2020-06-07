package com.gago.estadiosmapaaplicacion.Model;

public class Estadio {
    private long codigoEstadio;
    private String nombreEstadio;
    private String paisEstadio;
    private String ciudadEstadio;
    private String equipoEstadio;
    private int capacidadEstadio;
    private double latitudEstadio;
    private double longitudEstadio;

    public Estadio(String nombreEstadio, String paisEstadio, String ciudadEstadio, String equipoEstadio, int capacidadEstadio, double latitudEstadio, double longitudEstadio) {
        this.nombreEstadio = nombreEstadio;
        this.paisEstadio = paisEstadio;
        this.ciudadEstadio = ciudadEstadio;
        this.equipoEstadio = equipoEstadio;
        this.capacidadEstadio = capacidadEstadio;
        this.latitudEstadio = latitudEstadio;
        this.longitudEstadio = longitudEstadio;
    }

    public Estadio(long codigoEstadio, String nombreEstadio, String paisEstadio, String ciudadEstadio, String equipoEstadio, int capacidadEstadio, double latitudEstadio, double longitudEstadio) {
        this.codigoEstadio = codigoEstadio;
        this.nombreEstadio = nombreEstadio;
        this.paisEstadio = paisEstadio;
        this.ciudadEstadio = ciudadEstadio;
        this.equipoEstadio = equipoEstadio;
        this.capacidadEstadio = capacidadEstadio;
        this.latitudEstadio = latitudEstadio;
        this.longitudEstadio = longitudEstadio;
    }

    public long getCodigoEstadio() {
        return codigoEstadio;
    }

    public void setCodigoEstadio(long codigoEstadio) {
        this.codigoEstadio = codigoEstadio;
    }

    public String getNombreEstadio() {
        return nombreEstadio;
    }

    public void setNombreEstadio(String nombreEstadio) {
        this.nombreEstadio = nombreEstadio;
    }

    public String getPaisEstadio() {
        return paisEstadio;
    }

    public void setPaisEstadio(String paisEstadio) {
        this.paisEstadio = paisEstadio;
    }

    public String getCiudadEstadio() {
        return ciudadEstadio;
    }

    public void setCiudadEstadio(String ciudadEstadio) {
        this.ciudadEstadio = ciudadEstadio;
    }

    public String getEquipoEstadio() {
        return equipoEstadio;
    }

    public void setEquipoEstadio(String equipoEstadio) {
        this.equipoEstadio = equipoEstadio;
    }

    public int getCapacidadEstadio() {
        return capacidadEstadio;
    }

    public void setCapacidadEstadio(int capacidadEstadio) {
        this.capacidadEstadio = capacidadEstadio;
    }

    public double getLatitudEstadio() {
        return latitudEstadio;
    }

    public void setLatitudEstadio(double latitudEstadio) {
        this.latitudEstadio = latitudEstadio;
    }

    public double getLongitudEstadio() {
        return longitudEstadio;
    }

    public void setLongitudEstadio(double longitudEstadio) {
        this.longitudEstadio = longitudEstadio;
    }
}
