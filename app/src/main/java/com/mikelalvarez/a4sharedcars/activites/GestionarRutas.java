package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.adapters.GestionarRutasAdapter;
import com.mikelalvarez.a4sharedcars.databinding.RutasUsuarioItemBinding;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class GestionarRutas extends AppCompatActivity {

    Usuario usuarioLogeado;
    Realm realm;
    Bundle bundle;
    TextView txtnombre;
    Button btnAñadirRuta;
    RecyclerView recyclerView;
    Intent anadirRuta;
    RealmResults<Ruta> rutas;
    RealmList<Ruta> rutasUsuario;
    TextView txtVacio;
    Ruta rutaClick;
    Intent editarRuta;
    FloatingActionButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_rutas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        bundle = getIntent().getExtras();

        realm = Realm.getDefaultInstance();

        editarRuta = new Intent(this,EditarRuta.class);


        anadirRuta = new Intent(this,AnadirRuta.class);

        usuarioLogeado = realm.where(Usuario.class).equalTo("id",bundle.getInt("idUserGestionar")).findFirst();

        btnVolver = findViewById(R.id.btnVolverGestionarRuta);

        rutas = realm.where(Ruta.class).findAll();

        rutasUsuario = new RealmList<>();
        for (Ruta ruta: rutas){
            if (ruta.getConductor() == usuarioLogeado.getId()){
                rutasUsuario.add(ruta);
            }
        }


        txtVacio = findViewById(R.id.txtVacioGestionarRutas);
        txtnombre = findViewById(R.id.txtNombreUsuarioRutas);
        btnAñadirRuta = findViewById(R.id.btnNuevaRuta);
        recyclerView = findViewById(R.id.recyclerViewGestionRutas);

        txtnombre.setText(usuarioLogeado.getUsername());

        btnAñadirRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadirRuta.putExtra("idAñadirRuta",usuarioLogeado.getId());
                startActivity(anadirRuta);
            }
        });

        if (rutasUsuario.isEmpty()){
            txtVacio.setVisibility(View.VISIBLE);

        } else {
            txtVacio.setVisibility(View.INVISIBLE);
        }

        GestionarRutasAdapter gestionarRutasAdapter = new GestionarRutasAdapter(rutasUsuario, new GestionarRutasAdapter.GestinarRutasHolder.OnButtonClickListener() {
            @Override
            public void OnItemClickDelete(Ruta ruta) {
                rutaClick = ruta;
                onBackPressed();
            }

            @Override
            public void OnItemClickEdit(Ruta ruta) {
                editarRuta.putExtra("idRuta",ruta.getId());
                startActivity(editarRuta);
            }
        });
        recyclerView.setAdapter(gestionarRutasAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paginaPrincipal = new Intent(GestionarRutas.this,PaginaPrincipal.class);
                paginaPrincipal.putExtra("UserId",usuarioLogeado.getId());
                startActivity(paginaPrincipal);
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("borrar?")
                .setMessage("Seguro que quieres borrar la ruta?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        realm.beginTransaction();
                        rutaClick.deleteFromRealm();
                        realm.commitTransaction();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}