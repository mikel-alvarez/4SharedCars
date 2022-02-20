package com.mikelalvarez.a4sharedcars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class GestionarRutasAdapter extends RecyclerView.Adapter<GestionarRutasAdapter.GestinarRutasHolder>{{
}
    RealmList<Ruta> rutas;
    private Realm realm;
    GestinarRutasHolder.OnButtonClickListener btnAction;



    public GestionarRutasAdapter(RealmList<Ruta> rutas, GestinarRutasHolder.OnButtonClickListener btnAction) {
        this.rutas = rutas;
        this.btnAction = btnAction;
    }

    @NonNull
    @Override
    public GestinarRutasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rutas_usuario_item,parent,false);
        realm = Realm.getDefaultInstance();
        return new GestinarRutasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GestinarRutasHolder holder, int position) {
        Ruta ruta = rutas.get(position);
        holder.assignData(ruta, btnAction);
    }

    @Override
    public int getItemCount() {
        return this.rutas.size();
    }

    public static class GestinarRutasHolder extends RecyclerView.ViewHolder {
        private TextView ruta;
        private TextView fecha;
        private TextView hora;
        private TextView kms;
        private TextView huecos;
        private Button btnEliminar;
        private Button btnEditar;

        public GestinarRutasHolder(@NonNull View itemView) {
            super(itemView);
            this.ruta = (TextView) itemView.findViewById(R.id.txtRutaUsuarioItem);
            this.fecha = (TextView) itemView.findViewById(R.id.txtRutaUsuarioFechaItem);
            this.hora = (TextView) itemView.findViewById(R.id.txtRutaUsuarioHoraItem);
            this.kms = (TextView) itemView.findViewById(R.id.txtRutaUsuarioKmsItem);
            this.huecos = (TextView) itemView.findViewById(R.id.txtRutaUsuarioPlazasItem);
            this.btnEliminar = (Button) itemView.findViewById(R.id.btnEliminarRuta);
            this.btnEditar = (Button) itemView.findViewById(R.id.btnModificarRuta);
        }

        public void assignData(Ruta route, OnButtonClickListener btnAction) {
            ruta.setText(route.getRuta());
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            fecha.setText(date.format(route.getFecha()));
            hora.setText(route.getHora());
            kms.setText((int) route.getKms());
            int plazasLibre = route.getPlazas() - route.getPasajeros().size() + 1;
            huecos.setText(String.valueOf(plazasLibre));

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnAction.OnItemClickDelete(route);
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnAction.OnItemClickEdit(route);
                }
            });

        }

        public interface OnButtonClickListener{
            public void OnItemClickDelete(Ruta ruta);
            public void OnItemClickEdit(Ruta ruta);
        }
    }}


