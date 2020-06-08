package com.gago.estadiosmapaaplicacion.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.gago.estadiosmapaaplicacion.BaseDeDatos.ControladorBaseDeDatosEstadio;
import com.gago.estadiosmapaaplicacion.Model.Estadio;

import java.util.ArrayList;

public class EstadioViewModel extends AndroidViewModel {
    public MutableLiveData<ArrayList<Estadio>> listaEstadio = new MutableLiveData<>();
    ControladorBaseDeDatosEstadio elControlador;

    public EstadioViewModel(Application application) {
        super(application);
        elControlador = new ControladorBaseDeDatosEstadio(application);
    }

    public void actualizar() {
        listaEstadio.postValue(elControlador.optenerEstadiosDeBaseDeDatos());
    }
}
