package com.mikelalvarez.a4sharedcars.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.adapters.RutaRecyclerAdapter;
import com.mikelalvarez.a4sharedcars.model.Ruta;

import io.realm.RealmResults;


public class Reserva extends Fragment {

    RecyclerView recyclerView;
    View view;

    public Reserva() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reserva, container, false);

        recyclerView = view.findViewById(R.id.reservaRecycler);

        return view;
    }

    public void getData(RealmResults<Ruta> rutas, RutaRecyclerAdapter.OnItemClickListener itemClickListener, RutaRecyclerAdapter.OnItemClickListener buttomListener){
        RutaRecyclerAdapter rutaRecyclerAdapter = new RutaRecyclerAdapter(rutas,itemClickListener,buttomListener);
        recyclerView.setLayoutManager((new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false)));
        recyclerView.setAdapter(rutaRecyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(),DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}