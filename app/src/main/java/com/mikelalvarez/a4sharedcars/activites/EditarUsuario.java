package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditarUsuario extends AppCompatActivity {
    ImageView fotoPerfil;
    TextView nombre;
    TextView apellido;
    TextView nombreUsuario;
    TextView correo;
    TextView telefono;
    Usuario user;
    Button confirmar;
    Realm realm;
    RealmResults<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        fotoPerfil = (ImageView) findViewById(R.id.imgPerfilEditarUsuario);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        nombre = (TextView) findViewById(R.id.txtNombreEditarUsuario);
        apellido = (TextView) findViewById(R.id.txtApellidoEditarUsuario);
        nombreUsuario = (TextView) findViewById(R.id.txtNombreUsuarioEditarUsuario);
        correo = (TextView) findViewById(R.id.txtCorreoEditarUsuario);
        telefono = (TextView) findViewById(R.id.txtPhoneEditarUsuario);


        confirmar = (Button) findViewById(R.id.btnConfirmarEditarUsuario);
        Bundle bundle = getIntent().getExtras();
        int usuarioId = bundle.getInt("idEditarUsuario");
        realm = Realm.getDefaultInstance();
        user = realm.where(Usuario.class).equalTo("id", usuarioId).findFirst();
        listaUsuarios = realm.where(Usuario.class).findAll();

        nombre.setText(user.getNombre());
        apellido.setText(user.getApellido());
        nombreUsuario.setText(user.getUsername());
        correo.setText(user.getCorreo());
        telefono.setText(user.getTelefono());

        fotoPerfil.setImageResource(user.getImagen());

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario userChec = realm.where(Usuario.class).equalTo("id",usuarioId).findFirst();

                realm.beginTransaction();
                userChec.setNombre(nombre.getText().toString());
                userChec.setApellido(apellido.getText().toString());
                userChec.setUsername(nombreUsuario.getText().toString());
                userChec.setCorreo(nombreUsuario.getText().toString());
                userChec.setTelefono(telefono.getText().toString());
                realm.commitTransaction();

                Intent intent = new Intent(EditarUsuario.this,PaginaPrincipal.class);
                intent.putExtra("UserId",user.getId());
                startActivity(intent);
            }
        });



    }
}