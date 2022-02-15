package com.mikelalvarez.a4sharedcars.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mikelalvarez.a4sharedcars.fragments.MiUsuario;
import com.mikelalvarez.a4sharedcars.fragments.Ranking;
import com.mikelalvarez.a4sharedcars.fragments.Reserva;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import io.realm.Realm;
import io.realm.RealmResults;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numberOfTabs = behavior;

    }

    public void setFragmentReserva(RealmResults<Ruta> rutas, RutaRecyclerAdapter.OnItemClickListener reservaImgClickListener, RutaRecyclerAdapter.OnItemClickListener reservaButtomListener){

        Reserva reserva = (Reserva) getItem(0);
        reserva.getData(rutas,reservaImgClickListener,reservaButtomListener);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return new Reserva();
            }
            case 1:{
                return new MiUsuario();
            }
            case 2:{
                return new Ranking();
            }
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
