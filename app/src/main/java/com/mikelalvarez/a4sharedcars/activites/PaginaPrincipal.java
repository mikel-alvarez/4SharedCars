package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.adapters.RutaRecyclerAdapter;
import com.mikelalvarez.a4sharedcars.adapters.TabsAdapter;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import io.realm.Realm;

public class PaginaPrincipal extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabsAdapter myTabsAdapter;
    Realm realm;
    Usuario userLogeado;
    Intent otroUsuario;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bundle = getIntent().getExtras();

        realm = Realm.getDefaultInstance();

        userLogeado = realm.where(Usuario.class).equalTo("id",bundle.getInt("UserId")).findFirst();

        otroUsuario = new Intent(this,OtroUsuario.class);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("USUARIO"));
        tabLayout.addTab(tabLayout.newTab().setText("RESERVAR"));
        tabLayout.addTab(tabLayout.newTab().setText("RANKING"));

        RutaRecyclerAdapter.OnItemClickListener registroImgListener = new RutaRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ruta ruta, Usuario conductor) {
                otroUsuario.putExtra("idConductor",conductor.getId());
                otroUsuario.putExtra("idLogIn",userLogeado.getId());
                startActivity(otroUsuario);
            }
        };
        RutaRecyclerAdapter.OnItemClickListener registroBtnListener = new RutaRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ruta ruta, Usuario conductor) {
                if (conductor.getId() == userLogeado.getId()){
                    Toast.makeText(PaginaPrincipal.this, "No puedes unirte a tu propia ruta", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (conductor.getUsuariosVetados().contains(userLogeado.getId())){
                    Toast.makeText(PaginaPrincipal.this, "El conductor te tiene vetado", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ruta.getPasajeros().contains(userLogeado.getId())){
                    Toast.makeText(PaginaPrincipal.this, "Ya estas dentro", Toast.LENGTH_SHORT).show();
                    return;
                }
                ruta.addPasajero(userLogeado.getId());
                Toast.makeText(PaginaPrincipal.this, "Te has unido setisfatoriamente", Toast.LENGTH_SHORT).show();
            }
        };
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myTabsAdapter = new TabsAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),realm,registroImgListener,registroBtnListener);
        viewPager.setAdapter(myTabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}