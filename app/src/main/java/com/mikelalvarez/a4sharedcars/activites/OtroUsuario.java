package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.adapters.RutaOtroUsuarioAdapter;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class OtroUsuario extends AppCompatActivity {
    Realm realm;
    ImageView foto;
    TextView nombre;
    TextView username;
    TextView apellido;
    TextView puntos;
    RecyclerView recyclerview;
    FloatingActionButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otro_usuario);
        realm = Realm.getDefaultInstance();
        Bundle bundle = getIntent().getExtras();
        Integer id = bundle.getInt("idOtroUsuario");
        Integer idLogeado = bundle.getInt("idLogIn");

        Usuario usuario = realm.where(Usuario.class).equalTo("id",id).findFirst();
        RealmResults<Ruta> rutas = realm.where(Ruta.class).equalTo("conductor", id).findAll();

        nombre = (TextView) findViewById(R.id.txtNombreOtroUsuario);
        apellido = (TextView) findViewById(R.id.txtApellidoOtroUsuario);
        username = (TextView) findViewById(R.id.txtNombreUsuarioOtroUsuario);
        puntos = (TextView) findViewById(R.id.txtPuntosOtroUsuario);
        foto = (ImageView) findViewById(R.id.imgFotoOtroUsuario);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerViewOtroUsuario);
        RutaOtroUsuarioAdapter adapter = new RutaOtroUsuarioAdapter(rutas);

        recyclerview.setAdapter(adapter);

        nombre.setText(usuario.getNombre());
        apellido.setText(usuario.getApellido());
        username.setText(usuario.getUsername());
        puntos.setText(usuario.getPuntosC02().toString());
        foto.setImageResource(usuario.getImagen());

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paginaPrincipal = new Intent(OtroUsuario.this,PaginaPrincipal.class);
                paginaPrincipal.putExtra("UserId",idLogeado);
                startActivity(paginaPrincipal);
            }
        });
    }
}