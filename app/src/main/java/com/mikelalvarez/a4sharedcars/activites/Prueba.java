package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mikelalvarez.a4sharedcars.R;

public class Prueba extends AppCompatActivity {

    Intent mapa;
    Button btnMapas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        //Es un activity de pruebas
        mapa = new Intent(this,MapsActivity.class);

        btnMapas = findViewById(R.id.btnMapaPrueba);

        btnMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mapa);
            }
        });


    }
}