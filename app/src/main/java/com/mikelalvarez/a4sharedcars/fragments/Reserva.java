package com.mikelalvarez.a4sharedcars.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.activites.OtroUsuario;
import com.mikelalvarez.a4sharedcars.activites.PaginaPrincipal;
import com.mikelalvarez.a4sharedcars.adapters.RutaRecyclerAdapter;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import io.realm.Realm;
import io.realm.RealmResults;


public class Reserva extends Fragment {

    RecyclerView recyclerView;
    View view;
    RealmResults<Ruta> rutas;
    RutaRecyclerAdapter.OnItemClickListener registroImgListener;
    RutaRecyclerAdapter.OnItemClickListener registroBtnListener;
    Realm realm;
    int userLogeadoId;
    public Reserva() {
        // Required empty public constructor
    }






    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reserva, container, false);

        recyclerView = view.findViewById(R.id.reservaRecycler);


        realm = Realm.getDefaultInstance();

        rutas = realm.where(Ruta.class).findAll();

       registroImgListener = new RutaRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ruta ruta, Usuario conductor) {
                Intent otroUsuario = new Intent(view.getContext(), OtroUsuario.class);
                otroUsuario.putExtra("idOtroUsuario",conductor.getId());
                otroUsuario.putExtra("idLogIn",userLogeadoId);
                startActivity(otroUsuario);
            }
        };
        registroBtnListener = new RutaRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ruta ruta, Usuario conductor) {
                if (conductor.getId() == userLogeadoId){
                    Toast.makeText(view.getContext(), "No puedes unirte a tu propia ruta", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (conductor.getUsuariosVetados().contains(userLogeadoId)){
                    Toast.makeText(view.getContext(), "El conductor te tiene vetado", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ruta.getPasajeros().contains(userLogeadoId)){
                    Toast.makeText(view.getContext(), "Ya estas dentro", Toast.LENGTH_SHORT).show();
                    return;
                }
                realm.beginTransaction();
                ruta.addPasajero(userLogeadoId, view.getContext());
                realm.commitTransaction();
                Toast.makeText(view.getContext(), "Te has unido setisfatoriamente", Toast.LENGTH_SHORT).show();
            }
        };

        putData(rutas,registroImgListener,registroBtnListener);

        return view;
    }


    private void putData(RealmResults<Ruta> rutas, RutaRecyclerAdapter.OnItemClickListener imgClickListener, RutaRecyclerAdapter.OnItemClickListener buttomListener){
        RutaRecyclerAdapter rutaRecyclerAdapter = new RutaRecyclerAdapter(rutas,imgClickListener,buttomListener);
        recyclerView.setLayoutManager((new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false)));
        recyclerView.setAdapter(rutaRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        rutaRecyclerAdapter.notifyDataSetChanged();
    }
    public void idUserLogeado(int id){
        userLogeadoId = id;
    }
}