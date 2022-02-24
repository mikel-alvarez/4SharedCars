package com.mikelalvarez.a4sharedcars.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.activites.OtroUsuario;
import com.mikelalvarez.a4sharedcars.adapters.RankingRecycleAdapter;
import com.mikelalvarez.a4sharedcars.adapters.RutaRecyclerAdapter;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import io.realm.Realm;
import io.realm.RealmResults;


public class Ranking extends Fragment {

    RecyclerView recyclerView;
    View view;
    Realm realm;
    Usuario userLog;
    public Ranking() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_ranking, container, false);

        recyclerView = view.findViewById(R.id.recycleViewRanking);

        realm = Realm.getDefaultInstance();
        RealmResults<Usuario> usuarios = realm.where(Usuario.class).findAll().sort("puntosC02") ;
        Intent otroUsuario = new Intent(view.getContext(), OtroUsuario.class);
        getData(usuarios, new RankingRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Usuario user) {
                otroUsuario.putExtra("idOtroUsuario",user.getId());
                startActivity(otroUsuario);
            }
        });

        return view;
    }

    public void getData(RealmResults<Usuario> usuarios, RankingRecycleAdapter.OnItemClickListener imgClick){
        RankingRecycleAdapter rankingRecyclerAdapter = new RankingRecycleAdapter(usuarios,imgClick);
        recyclerView.setLayoutManager((new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false)));
        recyclerView.setAdapter(rankingRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void getLogUser(Usuario userLog){
        this.userLog = userLog;
    }
}