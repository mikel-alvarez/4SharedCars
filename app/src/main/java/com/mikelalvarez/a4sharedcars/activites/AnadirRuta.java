package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

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


        Ruta rut = new Ruta();
        rut.setConductor(conductor.getId());

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rut.setRuta(rutaNombre.getText().toString());
                rut.setHora(hora.getText().toString());

                // Control de Fecha (mayor a la de hoy )
                Date d = new Date(TimeUnit.SECONDS.toMillis(fecha.getDate()));
                long miliseconds = System.currentTimeMillis();
                Date hoy = new Date(miliseconds);
                if (d.getMonth() == hoy.getMonth() && (hoy.getDay() - d.getDay() <= 6)){
                    rut.setFecha(d);
                }else{
                    valid = false;
                    Toast.makeText(AnadirRuta.this, "Fecha no valida", Toast.LENGTH_SHORT).show();

                }

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
                    realm.beginTransaction();
                    realm.copyToRealm(rut);
                    realm.commitTransaction();
                }else{
                    valid = true;
                }
            }
        });


    }
}