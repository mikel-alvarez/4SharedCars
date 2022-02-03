package com.mikelalvarez.a4sharedcars.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import java.util.List;

import io.realm.RealmResults;

public class RutaRecyclerAdapter extends RecyclerView.Adapter<RutaRecyclerAdapter.RutaHolder>{

    private RealmResults<Ruta> rutaData;
    private AdapterView.OnItemClickListener itemListener;

    public RutaRecyclerAdapter(RealmResults<Ruta> listData, AdapterView.OnItemClickListener listener) {
        this.rutaData = listData;
        this.itemListener = listener;
    }

    @Override
    public RutaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ruta_item, parent, false);
        return new RutaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaHolder holder, int position) {
        holder.assignData(rutaData.get(position), itemListener);
    }

    @Override
    public int getItemCount() {
        return this.rutaData.size();
    }

    public class RutaHolder extends RecyclerView.ViewHolder {
        private ImageView fotoPerfil;
        private TextView nombre;
        private TextView apellido;
        private TextView fecha;
        private TextView horaSalida;
        private TextView ruta;
        private TextView huecosLibres;

        public RutaHolder(@NonNull View itemView) {
            super(itemView);
            this.fotoPerfil = (ImageView) itemView.findViewById(R.id.imgFotoPerfilUsuario);
            this.nombre = (TextView) itemView.findViewById(R.id.txtNombreUsuarioItem);
            this.apellido = (TextView) itemView.findViewById(R.id.txtApellidoUsuarioItem);
            this.fecha = (TextView) itemView.findViewById(R.id.txtFechaItem);
            this.horaSalida = (TextView) itemView.findViewById(R.id.txtHoraSalidaItem);
            this.ruta = (TextView) itemView.findViewById(R.id.txtRutaItem);
            this.huecosLibres = (TextView) itemView.findViewById(R.id.txtHuecosLibresItem);
        }

        private void assignData(@NonNull final Ruta route, @NonNull final Usuario user, final AdapterView.OnItemClickListener listener) {

            fotoPerfil.setImageResource(user.getImagen());
            nombre.setText(user.getNombre());
            apellido.setText(user.getApellido());
            fecha.setText(route.getFecha().toString());
            horaSalida.setText(route.getHora());
            ruta.setText(route.getRuta());
            huecosLibres.setText(route.getPlazas() - route.getPasajeros().size() + 1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(route, user, getAdapterPosition());
                }
            });

        }
    }

    public interface OnItemClickListener{
        public void onItemClick(Ruta ruta, Usuario usuario, int position);
    }
}
