package com.mikelalvarez.a4sharedcars.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mikelalvarez.a4sharedcars.fragments.MiUsuario;
import com.mikelalvarez.a4sharedcars.fragments.Ranking;
import com.mikelalvarez.a4sharedcars.fragments.Reserva;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numberOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new MiUsuario();
            case 1:return new Reserva();
            case 2:return new Ranking();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
