package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;

import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class EditarRuta extends AppCompatActivity {


    TextView txtSalida;
    TextView txtLlegada;
    TextView txtplazas;
    TextView txtKM;
    TextView txtHora;
    CalendarView calendario;
    Button btnEditar;
    Realm realm;
    Bundle bundle;
    Ruta rutaEditando;
    FloatingActionButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ruta);

        realm = Realm.getDefaultInstance();

        txtSalida = findViewById(R.id.txtLugarSalidaEditarRuta);
        txtLlegada = findViewById(R.id.txtLugarLlegadaEditarRuta);
        txtplazas = findViewById(R.id.txtPlazasEditarRuta);
        txtKM = findViewById(R.id.txtKmEditarRuta);
        txtHora = findViewById(R.id.txtHoraEditarRuta);
        calendario = findViewById(R.id.calendarioEditarRuta);
        btnEditar = findViewById(R.id.btnEditarUsuario);
        btnVolver = findViewById(R.id.btnVueltaEditarRuta);
        bundle = getIntent().getExtras();

        rutaEditando = realm.where(Ruta.class).equalTo("id",bundle.getInt("idRuta")).findFirst();
        String[] lugarRuta = rutaEditando.getRuta().split("-");
        txtSalida.setText(lugarRuta[0]);
        txtLlegada.setText(lugarRuta[1]);
        txtplazas.setText(rutaEditando.getPlazas());
        txtHora.setText(rutaEditando.getHora());
        txtKM.setText(String.valueOf(rutaEditando.getKms()));
        calendario.setDate(rutaEditando.getFecha().getTime());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtSalida.getText().toString().toLowerCase().equals("cuatrovientos") || !txtLlegada.getText().toString().toLowerCase().equals("cuatrovientos")){
                    Toast.makeText(EditarRuta.this, "La ruta tiene que pasar por cuatrovientos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtSalida.getText() == txtLlegada.getText()){
                    Toast.makeText(EditarRuta.this, "Tine que ser un lugar diferente de salida y llegada", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (9 > Integer.parseInt(txtplazas.getText().toString())){
                    Toast.makeText(EditarRuta.this, "No hay coches contantas plazas", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] horaSeparada = txtHora.getText().toString().split(":");
                if ( horaSeparada.length != 2  || Integer.parseInt(horaSeparada[0]) > 24 || Integer.parseInt(horaSeparada[1]) > 60) {
                    Toast.makeText(EditarRuta.this, "Tiene que ser una hora valida", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (calendario.getDate() < new Date().getTime()){
                    Toast.makeText(EditarRuta.this, "No puede ser para una fecha posterios a hoy", Toast.LENGTH_SHORT).show();
                    return;
                }
                realm.beginTransaction();
                rutaEditando.setRuta(txtSalida.getText() + "-" + txtLlegada.getText());
                rutaEditando.setPlazas(Integer.parseInt(txtplazas.getText().toString()));
                rutaEditando.setKms(Double.parseDouble(txtKM.getText().toString()));
                rutaEditando.setHora(txtHora.getText().toString());
                rutaEditando.setFecha(new Date(calendario.getDate()));
                realm.commitTransaction();
                Intent gestionarRuta =  new Intent(EditarRuta.this,GestionarRutas.class);
                gestionarRuta.putExtra("idUserGestionar",rutaEditando.getConductor());
                startActivity(gestionarRuta);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gestionarRuta =  new Intent(EditarRuta.this,GestionarRutas.class);
                gestionarRuta.putExtra("idUserGestionar",rutaEditando.getConductor());
                startActivity(gestionarRuta);
            }
        });
    }
}