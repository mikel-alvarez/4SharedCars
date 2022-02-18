package com.mikelalvarez.a4sharedcars.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RutaRecyclerAdapter extends RecyclerView.Adapter<RutaRecyclerAdapter.RutaHolder>{

    private RealmResults<Ruta> rutaData;
    private OnItemClickListener itemListener;
    private Realm realm;
    private OnItemClickListener buttomListener;

    public RutaRecyclerAdapter(RealmResults<Ruta> listData, OnItemClickListener imgListener,OnItemClickListener buttomListener) {
        this.rutaData = listData;
        this.itemListener = imgListener;
        this.buttomListener = buttomListener;
    }

    @Override
    public RutaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ruta_item, parent, false);
        realm = Realm.getDefaultInstance();
        return new RutaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaHolder holder, int position) {
        Ruta ruta = rutaData.get(position);
        Usuario usuario = realm.where(Usuario.class).equalTo("id",ruta.getConductor()).findFirst();
        holder.assignData(ruta,usuario, itemListener,buttomListener);
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
        private Button btnReservar;

        public RutaHolder(@NonNull View itemView) {
            super(itemView);
            this.fotoPerfil = (ImageView) itemView.findViewById(R.id.imgFotoPerfilUsuario);
            this.nombre = (TextView) itemView.findViewById(R.id.txtNombreUsuarioItem);
            this.apellido = (TextView) itemView.findViewById(R.id.txtApellidoUsuarioItem);
            this.fecha = (TextView) itemView.findViewById(R.id.txtFechaItem);
            this.horaSalida = (TextView) itemView.findViewById(R.id.txtHoraSalidaItem);
            this.ruta = (TextView) itemView.findViewById(R.id.txtRutaItem);
            this.huecosLibres = (TextView) itemView.findViewById(R.id.txtHuecosLibresItem);
            this.btnReservar = (Button) itemView.findViewById(R.id.btnReservaItem);
        }

        private void assignData(@NonNull final Ruta route, @NonNull final Usuario user, final OnItemClickListener listener, final  OnItemClickListener buttonListener) {


            fotoPerfil.setImageResource(user.getImagen());
            nombre.setText(user.getNombre());
            apellido.setText(user.getApellido());
            SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
            fecha.setText(formateadorFecha.format(route.getFecha()));
            horaSalida.setText(route.getHora());
            ruta.setText(route.getRuta());
            int plazasLibre = route.getPlazas() - route.getPasajeros().size() + 1;
            huecosLibres.setText(String.valueOf(plazasLibre));

            fotoPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(route, user);
                }
            });

            btnReservar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonListener.onItemClick(route,user);
                }
            });

        }
    }

    public interface OnItemClickListener{
        public void onItemClick(Ruta ruta, Usuario conductor);
    }
}
