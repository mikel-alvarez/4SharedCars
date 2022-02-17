package com.mikelalvarez.a4sharedcars.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.fragments.MiUsuario;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import io.realm.RealmResults;

public class GestionarRutasAdapter extends RecyclerView.Adapter<GestionarRutasAdapter.GestinarRutasHolder>{{
}
    RealmResults<Ruta> rutas;
    OnButtonClickListener btnEliminar;

    @NonNull
    @Override
    public GestinarRutasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GestinarRutasHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnButtonClickListener{
    }

    public class GestinarRutasHolder extends RecyclerView.ViewHolder {
        public GestinarRutasHolder(@NonNull View itemView) {
            super(itemView);
        }
    }}


