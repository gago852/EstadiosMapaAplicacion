package com.gago.estadiosmapaaplicacion.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gago.estadiosmapaaplicacion.Model.Estadio;

import java.util.ArrayList;

public class ControladorBaseDeDatosEstadio {
    private BaseDeDatosEstadioHelper estadioHelper;

    public ControladorBaseDeDatosEstadio(Context contexto) {
        this.estadioHelper =
                new BaseDeDatosEstadioHelper(contexto, ModeloBaseDeDatosEstadios.NOMBRE_BASE_DE_DATOS, null, 1);
    }

    public long agregarEstadioABaseDeDatos(Estadio estadio) {
        try {
            SQLiteDatabase baseDeDatosEstadio = estadioHelper.getWritableDatabase();
            ContentValues valoresEstadio = new ContentValues();
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_NOMBRE_ESTADIO, estadio.getNombreEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_PAIS_ESTADIO, estadio.getPaisEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_CIUDAD_ESTADIO, estadio.getCiudadEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_EQUIPO_ESTADIO, estadio.getEquipoEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_CAPACIDAD_ESTADIO, estadio.getCapacidadEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_LATITUD_ESTADIO, estadio.getLatitudEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_LONGITUD_ESTADIO, estadio.getLongitudEstadio());
            return baseDeDatosEstadio.insert(ModeloBaseDeDatosEstadios.NOMBRE_TABLA_BASE_DE_DATOS, null, valoresEstadio);
        } catch (Exception e) {
            return -1L;
        }
    }

    public int modificarEstadio(Estadio estadio) {
        try {
            SQLiteDatabase baseDeDatosEstadio = estadioHelper.getWritableDatabase();
            ContentValues valoresEstadio = new ContentValues();
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_NOMBRE_ESTADIO, estadio.getNombreEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_PAIS_ESTADIO, estadio.getPaisEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_CIUDAD_ESTADIO, estadio.getCiudadEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_EQUIPO_ESTADIO, estadio.getEquipoEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_CAPACIDAD_ESTADIO, estadio.getCapacidadEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_LATITUD_ESTADIO, estadio.getLatitudEstadio());
            valoresEstadio.put(ModeloBaseDeDatosEstadios.COLUMNA_LONGITUD_ESTADIO, estadio.getLongitudEstadio());

            String estadioAModificar = ModeloBaseDeDatosEstadios.COLUMNA_CODIGO_ESTADIO + " = ?";
            String[] elWhere = {String.valueOf(estadio.getCodigoEstadio())};
            return baseDeDatosEstadio.update(ModeloBaseDeDatosEstadios.NOMBRE_TABLA_BASE_DE_DATOS,
                    valoresEstadio, estadioAModificar, elWhere);
        } catch (Exception e) {
            return 0;
        }
    }

    public int borrarEstadio(Estadio estadio) {
        try {
            SQLiteDatabase baseDeDatosEstadio = estadioHelper.getWritableDatabase();
            String[] elWhere = {String.valueOf(estadio.getCodigoEstadio())};
            return baseDeDatosEstadio.delete(ModeloBaseDeDatosEstadios.NOMBRE_TABLA_BASE_DE_DATOS
                    , ModeloBaseDeDatosEstadios.COLUMNA_CODIGO_ESTADIO + " = ?", elWhere);
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Estadio> optenerEstadiosDeBaseDeDatos() {
        ArrayList<Estadio> estadios = new ArrayList<>();
        SQLiteDatabase baseDeDatosEstadios = estadioHelper.getReadableDatabase();

        String[] lasColumnasDeLaTabla = {ModeloBaseDeDatosEstadios.COLUMNA_CODIGO_ESTADIO,
                ModeloBaseDeDatosEstadios.COLUMNA_NOMBRE_ESTADIO, ModeloBaseDeDatosEstadios.COLUMNA_PAIS_ESTADIO,
                ModeloBaseDeDatosEstadios.COLUMNA_CIUDAD_ESTADIO, ModeloBaseDeDatosEstadios.COLUMNA_EQUIPO_ESTADIO
                , ModeloBaseDeDatosEstadios.COLUMNA_CAPACIDAD_ESTADIO, ModeloBaseDeDatosEstadios.COLUMNA_LATITUD_ESTADIO,
                ModeloBaseDeDatosEstadios.COLUMNA_LONGITUD_ESTADIO};

        Cursor elCunsorDeLaBase = baseDeDatosEstadios.query(ModeloBaseDeDatosEstadios.NOMBRE_TABLA_BASE_DE_DATOS,
                lasColumnasDeLaTabla, null, null, null, null, null);

        if (elCunsorDeLaBase == null) {
            return estadios;
        }

        if (!elCunsorDeLaBase.moveToFirst()) {
            return estadios;
        }

        do {
            Estadio estadio = new Estadio(elCunsorDeLaBase.getLong(0), elCunsorDeLaBase.getString(1),
                    elCunsorDeLaBase.getString(2), elCunsorDeLaBase.getString(3),
                    elCunsorDeLaBase.getString(4), elCunsorDeLaBase.getInt(5),
                    elCunsorDeLaBase.getDouble(6), elCunsorDeLaBase.getDouble(7));
            estadios.add(estadio);
        } while (elCunsorDeLaBase.moveToNext());
        elCunsorDeLaBase.close();
        return estadios;
    }
}
