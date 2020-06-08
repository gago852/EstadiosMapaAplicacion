package com.gago.estadiosmapaaplicacion.View.UI.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gago.estadiosmapaaplicacion.BaseDeDatos.ControladorBaseDeDatosEstadio;
import com.gago.estadiosmapaaplicacion.Model.Estadio;
import com.gago.estadiosmapaaplicacion.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    EditText edNombreEstadio, edCiudadEstadio, edEquipoEstadio, edCapacidadEstadio;
    Spinner spPaisEstadio;
    Button btGuardar, btCancelar;

    ControladorBaseDeDatosEstadio controlador;

    String paisSeleccionado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edNombreEstadio = view.findViewById(R.id.edNombre);
        edCiudadEstadio = view.findViewById(R.id.edCiudad);
        edEquipoEstadio = view.findViewById(R.id.edEquipo);
        edCapacidadEstadio = view.findViewById(R.id.edCapacidad);
        spPaisEstadio = view.findViewById(R.id.spPais);
        btGuardar = view.findViewById(R.id.btGuardar);
        btCancelar = view.findViewById(R.id.btCancelar);

        controlador = new ControladorBaseDeDatosEstadio(getContext());

        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        ArrayAdapter<String> spinnerAdaptador = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, countries);
        spPaisEstadio.setAdapter(spinnerAdaptador);
        spPaisEstadio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paisSeleccionado = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btGuardar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btGuardar:
                try {
                    int capacidad = edCapacidadEstadio.getText().toString().isEmpty() ? 0 : Integer.parseInt(edCapacidadEstadio.getText().toString());
                    Geocoder geocoder = new Geocoder(getContext());
                    List<Address> direcciones = geocoder.getFromLocationName(edNombreEstadio.getText().toString() + " ,"
                            + edCiudadEstadio.getText().toString() + " " + paisSeleccionado, 1);
                    Address add = direcciones.get(0);
                    Estadio estadio = new Estadio(edNombreEstadio.getText().toString()
                            , paisSeleccionado, edCiudadEstadio.getText().toString(), edEquipoEstadio.getText().toString()
                            , capacidad, add.getLatitude(), add.getLongitude());
                    long retorno = controlador.agregarEstadioABaseDeDatos(estadio);
                    if (retorno != -1) {
                        Toast.makeText(getContext(), "registro guardado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "registro fallido", Toast.LENGTH_SHORT).show();
                    }
                    limpieza();
                } catch (NumberFormatException numEx) {
                    Toast.makeText(getContext(), "numero muy grande", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "error encontrando estadio", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btCancelar:
                limpieza();
                break;
        }
    }

    private void limpieza() {
        edNombreEstadio.setText("");
        edCiudadEstadio.setText("");
        edEquipoEstadio.setText("");
        edCapacidadEstadio.setText("");
    }
}
