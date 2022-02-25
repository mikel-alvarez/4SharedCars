package com.mikelalvarez.a4sharedcars.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RankingRecycleAdapter extends RecyclerView.Adapter<RankingRecycleAdapter.RutaHolder>{

    OnItemClickListener imgClick;
    RealmResults<Usuario> usuarios;


    @NonNull
    @Override
    public RankingRecycleAdapter.RutaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item,parent,false);

        return new RutaHolder(view);
    }

    public RankingRecycleAdapter( RealmResults<Usuario> usuarios, OnItemClickListener imgClick) {
        this.imgClick = imgClick;
        this.usuarios = usuarios.sort("puntosC02", Sort.DESCENDING);

    }

    @Override
    public void onBindViewHolder(@NonNull RankingRecycleAdapter.RutaHolder holder, int position) {

        holder.assingData(usuarios.get(position),imgClick);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class RutaHolder extends RecyclerView.ViewHolder {

        TextView lblNombre;
        TextView lblApellido;
        TextView lblPuntos;
        ImageView imgAvatar;

        public RutaHolder(@NonNull View itemView) {

            super(itemView);

            lblNombre = itemView.findViewById(R.id.lblNombreRankingItem);
            lblApellido = itemView.findViewById(R.id.lblApellidoRankingItem);
            lblPuntos = itemView.findViewById(R.id.lblPuntosRankingItem);
            imgAvatar = itemView.findViewById(R.id.imgAvatarRankingItem);
        }

        public void assingData(Usuario user,OnItemClickListener imgClick){

            if (user != null){
                lblNombre.setText(user.getNombre());
                lblApellido.setText(user.getApellido());
                lblPuntos.setText("200");
                imgAvatar.setImageResource(user.getImagen());
            }



            imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgClick.onItemClick(user);
                }
            });

        }

    }

    public interface OnItemClickListener{
        public void onItemClick( Usuario user);
    }
}
