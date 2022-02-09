package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.adapters.RankingRecycleAdapter;
import com.mikelalvarez.a4sharedcars.adapters.RutaRecyclerAdapter;
import com.mikelalvarez.a4sharedcars.adapters.TabsAdapter;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.security.Principal;

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
        tabLayout.addTab(tabLayout.newTab().setText("RESERVAR"));
        tabLayout.addTab(tabLayout.newTab().setText("USUARIO"));
        tabLayout.addTab(tabLayout.newTab().setText("RANKING"));

        RutaRecyclerAdapter.OnItemClickListener registroImgListener = new RutaRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ruta ruta, Usuario conductor) {
                otroUsuario.putExtra("idOtroUser",conductor.getId());
                otroUsuario.putExtra("idLogIn",userLogeado.getId());
                startActivity(otroUsuario);
            }
        };

        RankingRecycleAdapter.OnItemClickListener rankingImgClick = new RankingRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Usuario user) {
                otroUsuario.putExtra("idOtroUser",user.getId());
                otroUsuario.putExtra("idLogIn",user.getId());
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
                ruta.addPasajero(userLogeado.getId(), PaginaPrincipal.this);
                Toast.makeText(PaginaPrincipal.this, "Te has unido setisfatoriamente", Toast.LENGTH_SHORT).show();
            }
        };
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myTabsAdapter = new TabsAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),realm,registroImgListener,registroBtnListener,rankingImgClick);
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

    public boolean OnCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.buscar:

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_buscar, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

                return true;

            case R.id.mapa:
                return true;

            case R.id.cerrar_sesion:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}