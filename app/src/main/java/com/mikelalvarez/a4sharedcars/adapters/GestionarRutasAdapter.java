package com.mikelalvarez.a4sharedcars.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.fragments.MiUsuario;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import io.realm.RealmResults;

public class GestionarRutasAdapter extends RecyclerView.Adapter<GestionarRutasAdapter.GestinarRutasHolder>{{
}
    RealmResults<Ruta> rutas;
    OnButtonClickListener btnEliminar;

    public interface OnButtonClickListener{
    }

    public class GestinarRutasHolder {
    }


