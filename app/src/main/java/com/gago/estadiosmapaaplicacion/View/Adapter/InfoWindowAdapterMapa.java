package com.gago.estadiosmapaaplicacion.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gago.estadiosmapaaplicacion.Model.Estadio;
import com.gago.estadiosmapaaplicacion.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapterMapa implements GoogleMap.InfoWindowAdapter {
    LayoutInflater layoutInflater;
    TextView tvInfoTitulo, tvInfoDescripcion;

    public InfoWindowAdapterMapa(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = layoutInflater.inflate(R.layout.info_window_estadio, null);
        tvInfoTitulo = view.findViewById(R.id.tvInfoTitulo);
        tvInfoDescripcion = view.findViewById(R.id.tvInfoDescripcion);
        tvInfoTitulo.setText(marker.getTitle());
        tvInfoDescripcion.setText(marker.getSnippet());
        return view;
    }
}
