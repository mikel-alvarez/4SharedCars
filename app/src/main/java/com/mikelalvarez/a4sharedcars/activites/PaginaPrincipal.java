package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.adapters.TabsAdapter;

import android.os.Bundle;

public class PaginaPrincipal extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabsAdapter myTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("USUARIO"));
        tabLayout.addTab(tabLayout.newTab().setText("RESERVAR"));
        tabLayout.addTab(tabLayout.newTab().setText("RANKING"));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myTabsAdapter = new TabsAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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