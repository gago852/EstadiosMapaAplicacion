package com.gago.estadiosmapaaplicacion.View.UI.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gago.estadiosmapaaplicacion.BaseDeDatos.ControladorBaseDeDatosEstadio;
import com.gago.estadiosmapaaplicacion.Model.Estadio;
import com.gago.estadiosmapaaplicacion.R;
import com.gago.estadiosmapaaplicacion.View.Adapter.EstadioAdapter;
import com.gago.estadiosmapaaplicacion.ViewModel.EstadioViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    EstadioAdapter estadioAdapter;

    EstadioViewModel estadioViewModel;

    RecyclerView rvEstadios;

    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEstadios = view.findViewById(R.id.rvEstadios);
        rvEstadios.setLayoutManager(new LinearLayoutManager(getContext()));

        estadioViewModel = new ViewModelProvider(this).get(EstadioViewModel.class);
        estadioViewModel.actualizar();

        estadioAdapter = new EstadioAdapter();
        rvEstadios.setAdapter(estadioAdapter);

        observerViewModel();
    }

    private void observerViewModel() {
        estadioViewModel.listaEstadio.observe(getViewLifecycleOwner(), new Observer<ArrayList<Estadio>>() {
            @Override
            public void onChanged(ArrayList<Estadio> estadios) {
                if (estadios != null) {
                    estadioAdapter.updateData(estadios);
                }
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                modificarEstadio(item.getOrder());
                break;
            case 2:
                borrarEstadio(item.getOrder());
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void modificarEstadio(int order) {
        ControladorBaseDeDatosEstadio controlador = new ControladorBaseDeDatosEstadio(getContext());
        Bundle bundle = new Bundle();
        bundle.putSerializable("estadio", controlador.optenerEstadiosDeBaseDeDatos().get(order));
        Navigation.findNavController(getView()).navigate(R.id.modificarDialogFragment, bundle);
    }

    private void borrarEstadio(int order) {
        ControladorBaseDeDatosEstadio controlador = new ControladorBaseDeDatosEstadio(getContext());
        int retorno = controlador.borrarEstadio(controlador.optenerEstadiosDeBaseDeDatos().get(order));
        estadioAdapter.updateData(controlador.optenerEstadiosDeBaseDeDatos());
        if (retorno == 1) {
            Toast.makeText(getContext(), "registro eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "error al borrar", Toast.LENGTH_SHORT).show();
        }
    }
}
