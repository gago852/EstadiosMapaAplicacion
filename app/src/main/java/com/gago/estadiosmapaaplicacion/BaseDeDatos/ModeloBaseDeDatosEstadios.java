package com.gago.estadiosmapaaplicacion.BaseDeDatos;

public class ModeloBaseDeDatosEstadios {

    public static final String NOMBRE_BASE_DE_DATOS = "estadiosbasededatos";
    public static final String NOMBRE_TABLA_BASE_DE_DATOS = "estadiostabla";
    public static final String COLUMNA_CODIGO_ESTADIO = "codigoestadio";
    public static final String COLUMNA_NOMBRE_ESTADIO = "nombreestadio";
    public static final String COLUMNA_PAIS_ESTADIO = "paisestadio";
    public static final String COLUMNA_CIUDAD_ESTADIO = "ciudadestadio";
    public static final String COLUMNA_EQUIPO_ESTADIO = "equipoestadio";
    public static final String COLUMNA_CAPACIDAD_ESTADIO = "capacidadestadio";
    public static final String COLUMNA_LATITUD_ESTADIO = "latitudestadio";
    public static final String COLUMNA_LONGITUD_ESTADIO = "longitudestadio";

    public static final String CREADOR_TABLA_BASE_DE_DATOS = "CREATE TABLE " + NOMBRE_TABLA_BASE_DE_DATOS
            + " ( " + COLUMNA_CODIGO_ESTADIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_NOMBRE_ESTADIO + " TEXT, " + COLUMNA_PAIS_ESTADIO + " TEXT, " +
            COLUMNA_CIUDAD_ESTADIO + " TEXT, " + COLUMNA_EQUIPO_ESTADIO + " TEXT, " +
            COLUMNA_CAPACIDAD_ESTADIO + " INTEGER, " + COLUMNA_LATITUD_ESTADIO + " REAL, " +
            COLUMNA_LONGITUD_ESTADIO + " REAL)";
}
