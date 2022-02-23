package com.mikelalvarez.a4sharedcars.activites;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;

public class AnadirRuta extends AppCompatActivity {
    Realm realm;
    CalendarView fecha;
    EditText rutaNombre;
    EditText km;
    EditText hora;
    EditText plazas;
    Usuario conductor;
    Boolean valid;
    Button añadir;
    FloatingActionButton btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ruta);
        realm = Realm.getDefaultInstance();
        Bundle bundle = getIntent().getExtras();
        Integer id = bundle.getInt("idAñadirRuta");
        conductor = realm.where(Usuario.class).equalTo("id",id).findFirst();

        fecha = (CalendarView) findViewById(R.id.calendarioAñadirRuta);
        rutaNombre = (EditText) findViewById(R.id.txtRutaAñadirRuta);
        km = (EditText) findViewById(R.id.txtKmAñadirRuta);
        hora = (EditText) findViewById(R.id.txtHoraAñadirRuta);
        plazas = (EditText) findViewById(R.id.txtPlazasAñadirRuta);
        añadir = (Button) findViewById(R.id.btnAñadirAñadirRuta);
        valid = true;
        btnVolver = findViewById(R.id.btnVolverAnadirRuta);


        Ruta rut = new Ruta();
        rut.setConductor(conductor.getId());

        fecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                LocalDate hoy = LocalDate.now();
                if (month == hoy.getMonth().getValue() - 1 && (dayOfMonth - hoy.getDayOfMonth()  <= 6 || dayOfMonth -  hoy.getDayOfMonth() >= 0 )){
                    rut.setFecha(new Date(hoy.getYear(), month, hoy.getDayOfMonth()));
                }else{
                    valid = false;
                    Toast.makeText(AnadirRuta.this, "Fecha no valida", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestionar = new Intent(AnadirRuta.this,GestionarRutas.class);
                gestionar.putExtra("idUserGestionar",id);
                startActivity(gestionar);
            }
        });

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rut.setRuta(rutaNombre.getText().toString());
                rut.setHora(hora.getText().toString());

                // Control de PLazas (int , +, <9)
                try{
                    Integer pla = Integer.parseInt(plazas.getText().toString());
                    if (pla > 0 && pla < 9 ){
                        rut.setPlazas(pla);
                    }else{
                        valid = false;
                        Toast.makeText(AnadirRuta.this, "plazas de 1 a 8", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    valid = false;
                    Toast.makeText(AnadirRuta.this, "Plazas tiene que ser numerico", Toast.LENGTH_SHORT).show();
                };

                // Control de Km (Double, +)
                try{
                    Double kms = Double.parseDouble(km.getText().toString());
                    if (kms > 0){
                        rut.setKms(kms);
                    }else{
                        valid = false;
                        Toast.makeText(AnadirRuta.this, "km mayour 0", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    valid = false;
                    Toast.makeText(AnadirRuta.this, "Km tiene que ser numerico", Toast.LENGTH_SHORT).show();
                };

                // Control de Usuario (Solo puede realizar una de ida y otra de buelta (rutaNOmbre = Cuatrovientos) a horas diferentes)
                if (valid == true){
                    RealmResults<Ruta> rutas = realm.where(Ruta.class).equalTo("conductor", id).equalTo("fecha", rut.getFecha()).findAll();
                    if (rutas.size() > 2){
                        valid = false;
                        Toast.makeText(AnadirRuta.this, "Ya hay rutas planeadas", Toast.LENGTH_SHORT).show();
                    }else{
                        if (rutas.first().getHora() == rut.getHora()){
                            valid = false;
                            Toast.makeText(AnadirRuta.this, "Ya hay rutas planeadas", Toast.LENGTH_SHORT).show();
                        }else if (rutas.first().getRuta().toUpperCase() != "CUATROVIENTOS" && rut.getRuta().toUpperCase() != "CUATROVIENTOS"){
                            valid = false;
                            Toast.makeText(AnadirRuta.this, "Ya hay una ruta de ida", Toast.LENGTH_SHORT).show();
                        }else if (rutas.first().getRuta().toUpperCase() != "CUATROVIENTOS" && rut.getRuta().toUpperCase() != "CUATROVIENTOS"){
                            valid = false;
                            Toast.makeText(AnadirRuta.this, "Ya hay una ruta de vuelta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                // Si todos los controles OK agregar la ruta al realm
                if (valid == true){
                    if (rut.getFecha() != null) {
                        realm.beginTransaction();
                        realm.copyToRealm(rut);
                        realm.commitTransaction();
                    }else{
                        Toast.makeText(AnadirRuta.this, "Fecha no valida", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    valid = true;
                }
            }
        });


    }
}