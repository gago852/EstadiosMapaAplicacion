package com.gago.estadiosmapaaplicacion.View.Adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gago.estadiosmapaaplicacion.Model.Estadio;
import com.gago.estadiosmapaaplicacion.R;

import java.util.ArrayList;

public class EstadioAdapter extends RecyclerView.Adapter<EstadioAdapter.EstadioViewHolder> {
    ArrayList<Estadio> lista=new ArrayList<>();

    @NonNull
    @Override
    public EstadioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estadio, null, false);
        return new EstadioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstadioViewHolder holder, int position) {
        holder.tvItemNombreEstadio.setText(lista.get(position).getNombreEstadio());
        holder.tvItemUbicacionEstadio.setText(lista.get(position).getCiudadEstadio() + ", " + lista.get(position).getPaisEstadio());
        holder.tvItemEquipoEstadio.setText(lista.get(position).getEquipoEstadio());
        holder.tvItemCapacidadEstadio.setText(Integer.toString(lista.get(position).getCapacidadEstadio()));

    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void updateData(ArrayList<Estadio> datos) {
        lista.clear();
        lista.addAll(datos);
        notifyDataSetChanged();
    }

    public class EstadioViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView tvItemNombreEstadio, tvItemUbicacionEstadio, tvItemEquipoEstadio, tvItemCapacidadEstadio;

        public EstadioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemNombreEstadio = itemView.findViewById(R.id.tvItemNombreEstadio);
            tvItemUbicacionEstadio = itemView.findViewById(R.id.tvItemUbicacionEstadio);
            tvItemEquipoEstadio = itemView.findViewById(R.id.tvItemEquipoEstadio);
            tvItemCapacidadEstadio = itemView.findViewById(R.id.tvItemCapacidadEstadio);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Acciones");
            menu.add(0, 1, getAdapterPosition(), "Actualizar");
            menu.add(0, 2, getAdapterPosition(), "Borrar");
        }


    }
}
