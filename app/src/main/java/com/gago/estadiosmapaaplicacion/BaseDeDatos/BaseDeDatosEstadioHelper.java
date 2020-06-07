package com.gago.estadiosmapaaplicacion.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDeDatosEstadioHelper extends SQLiteOpenHelper {

    public BaseDeDatosEstadioHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase liteDatabase) {
        liteDatabase.execSQL(ModeloBaseDeDatosEstadios.CREADOR_TABLA_BASE_DE_DATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase liteDatabase, int oldVersion, int newVersion) {

    }
}
