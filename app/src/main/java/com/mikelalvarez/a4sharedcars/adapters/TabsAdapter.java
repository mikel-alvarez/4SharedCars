package com.mikelalvarez.a4sharedcars.adapters;

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

public class TabsAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private Realm realm;
    private RutaRecyclerAdapter.OnItemClickListener reservaImgClick;
    private RutaRecyclerAdapter.OnItemClickListener reservaBtnClick;
    private RankingRecycleAdapter.OnItemClickListener rankingImgClick;

    public TabsAdapter(@NonNull FragmentManager fm, int behavior, Realm realm, RutaRecyclerAdapter.OnItemClickListener reservaImgClick, RutaRecyclerAdapter.OnItemClickListener reservaButtonClick, RankingRecycleAdapter.OnItemClickListener rankingImgClick) {
        super(fm, behavior);
        this.numberOfTabs = behavior;
        this.realm = realm;
        this.reservaImgClick = reservaImgClick;
        this.reservaBtnClick = reservaButtonClick;
        this.rankingImgClick = rankingImgClick;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{

                return new MiUsuario();
            }
            case 1:{
                Reserva reserva = new Reserva();
                reserva.getData(realm.where(Ruta.class).findAll(),reservaImgClick,reservaBtnClick);
                return reserva;
            }
            case 2:{
                Ranking ranking = new Ranking();
                ranking.getData(realm.where(Usuario.class).findAll(),rankingImgClick);
                return ranking;
            }
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
