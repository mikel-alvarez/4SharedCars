package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ruta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        txtSalida = findViewById(R.id.txtLugarSalidaEditarRuta);
        txtLlegada = findViewById(R.id.txtLugarLlegadaEditarRuta);
        txtplazas = findViewById(R.id.txtPlazasEditarRuta);
        txtKM = findViewById(R.id.txtKmEditarRuta);
        txtHora = findViewById(R.id.txtHoraEditarRuta);
        calendario = findViewById(R.id.calendarioEditarRuta);
        btnEditar = findViewById(R.id.btnEditarUsuario);

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
                }
                if (9 > txtplazas.getText().toString()){

                }

            }
        });
    }
}