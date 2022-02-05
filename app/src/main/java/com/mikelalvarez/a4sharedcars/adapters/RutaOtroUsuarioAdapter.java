package com.mikelalvarez.a4sharedcars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;

import java.util.List;

import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RutaOtroUsuarioAdapter extends RecyclerView.Adapter<RutaOtroUsuarioAdapter.RutaHolder>{
    RealmResults<Ruta> listaRuta;

    public RutaOtroUsuarioAdapter(RealmResults<Ruta> listaRuta) {
        this.listaRuta = listaRuta;
    }

    @NonNull
    @Override
    public RutaOtroUsuarioAdapter.RutaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ruta_otrousuario_item, parent, false);
        return new RutaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaOtroUsuarioAdapter.RutaHolder holder, int position) {
        holder.AsignarRuta(listaRuta.get(position));
    }

    @Override
    public int getItemCount() {
        return listaRuta.size();
    }

    public class RutaHolder extends RecyclerView.ViewHolder {
        TextView ruta;
        TextView fecha;
        TextView hora;
        TextView km;
        public RutaHolder(@NonNull View itemView) {
            super(itemView);
            ruta = (TextView) itemView.findViewById(R.id.txtRutaItemOU);
            fecha = (TextView) itemView.findViewById(R.id.txtFechaItemOU);
            hora = (TextView) itemView.findViewById(R.id.txtHoraItemOU);
            km = (TextView) itemView.findViewById(R.id.txtKmItemOU);
        }

        public void AsignarRuta(Ruta rut){
            ruta.setText(rut.getRuta());
            fecha.setText(rut.getFecha().toString());
            hora.setText(rut.getHora());
            km.setText("Km:" + rut.getKms());
        }
    }
}
