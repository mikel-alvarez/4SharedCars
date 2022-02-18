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
    private Fragment[] fragment = {new Reserva(), new MiUsuario(), new Ranking()};

    public TabsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numberOfTabs = behavior;

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
