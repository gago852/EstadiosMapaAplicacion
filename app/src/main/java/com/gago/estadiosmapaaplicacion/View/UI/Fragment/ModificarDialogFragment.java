package com.gago.estadiosmapaaplicacion.View.UI.Fragment;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.gago.estadiosmapaaplicacion.BaseDeDatos.ControladorBaseDeDatosEstadio;
import com.gago.estadiosmapaaplicacion.Model.Estadio;
import com.gago.estadiosmapaaplicacion.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class ModificarDialogFragment extends DialogFragment {

    Toolbar modToolbar;
    TextView edModNombreEstadio, edModCiudadEstadio, edModEquipoEstadio, edModCapacidadEstadio;
    Spinner spModPaisEstadio;
    Button btModGuardar;

    String paisSeleccionado;

    public ModificarDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modificar_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        modToolbar = view.findViewById(R.id.toolbarModificar);
        modToolbar.setNavigationIcon(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_close));
        modToolbar.setTitleTextColor(Color.WHITE);
        modToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        edModNombreEstadio = view.findViewById(R.id.edModNombreEstadio);
        edModCiudadEstadio = view.findViewById(R.id.edModCiudadEstadio);
        edModEquipoEstadio = view.findViewById(R.id.edModEquipoEstadio);
        edModCapacidadEstadio = view.findViewById(R.id.edModCapacidadEstadio);

        spModPaisEstadio = view.findViewById(R.id.spModPaisEstadio);

        btModGuardar = view.findViewById(R.id.btModGuardar);

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
        spModPaisEstadio.setAdapter(spinnerAdaptador);


        final Estadio estadio = (Estadio) getArguments().getSerializable("estadio");

        edModNombreEstadio.setText(estadio.getNombreEstadio());
        edModCiudadEstadio.setText(estadio.getCiudadEstadio());
        edModEquipoEstadio.setText(estadio.getEquipoEstadio());
        edModCapacidadEstadio.setText(String.valueOf(estadio.getCapacidadEstadio()));

        int posicion = countries.indexOf(estadio.getPaisEstadio());
        posicion = posicion != -1 ? posicion : 0;
        spModPaisEstadio.setSelection(posicion);

        spModPaisEstadio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paisSeleccionado = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btModGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControladorBaseDeDatosEstadio controlador = new ControladorBaseDeDatosEstadio(getContext());
                try {
                    int capacidad = edModCapacidadEstadio.getText().toString().isEmpty() ? 0 :
                            Integer.parseInt(edModCapacidadEstadio.getText().toString());
                    Geocoder geocoder = new Geocoder(getContext());
                    List<Address> direcciones = geocoder.getFromLocationName(edModNombreEstadio.getText().toString() + " ,"
                            + edModCiudadEstadio.getText().toString() + " " + paisSeleccionado, 1);
                    Address add = direcciones.get(0);
                    Estadio estadio1 = new Estadio(estadio.getCodigoEstadio(), edModNombreEstadio.getText().toString()
                            , paisSeleccionado, edModCiudadEstadio.getText().toString(), edModEquipoEstadio.getText().toString()
                            , capacidad, add.getLatitude(), add.getLongitude());
                    int retorno = controlador.modificarEstadio(estadio1);
                    if (retorno == 1) {
                        Toast.makeText(getContext(), "actualizacion exitoza", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }else{
                        Toast.makeText(getContext(), "actualizacion fallida", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException numEx) {
                    Toast.makeText(getContext(), "numero muy largo", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "fallo a encontrar estadio", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
