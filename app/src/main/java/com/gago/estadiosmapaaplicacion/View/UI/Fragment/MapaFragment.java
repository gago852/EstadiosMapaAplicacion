package com.gago.estadiosmapaaplicacion.View.UI.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gago.estadiosmapaaplicacion.BaseDeDatos.ControladorBaseDeDatosEstadio;
import com.gago.estadiosmapaaplicacion.Model.Estadio;
import com.gago.estadiosmapaaplicacion.R;
import com.gago.estadiosmapaaplicacion.View.Adapter.InfoWindowAdapterMapa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mapa;
    private boolean estaMostrandoVentanaInfo = false;

    private ControladorBaseDeDatosEstadio controlador;

    public MapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment fragmentoMapa = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        controlador = new ControladorBaseDeDatosEstadio(getContext());

        fragmentoMapa.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.custom_map));
        googleMap.setInfoWindowAdapter(new InfoWindowAdapterMapa(getLayoutInflater()));
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!estaMostrandoVentanaInfo) {
                    marker.showInfoWindow();
                    estaMostrandoVentanaInfo = true;
                } else {
                    marker.hideInfoWindow();
                    estaMostrandoVentanaInfo = false;
                }
                return false;
            }
        });

        ArrayList<Estadio> listaEstadios = controlador.optenerEstadiosDeBaseDeDatos();

        Marker marcador;
        for (Estadio estadio : listaEstadios) {
            LatLng coordenadasEstadio = new LatLng(estadio.getLatitudEstadio(), estadio.getLongitudEstadio());
            MarkerOptions marcadorEstadio = new MarkerOptions().position(coordenadasEstadio).title(estadio.getNombreEstadio())
                    .snippet("Equipo: " + estadio.getEquipoEstadio() + "\n Capacidad: " + estadio.getCapacidadEstadio());
            marcador = googleMap.addMarker(marcadorEstadio);
        }

        LatLng camara = new LatLng(3.9288954, -75.3191851);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camara, 5));

    }
}
