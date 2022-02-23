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
    FloatingActionButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        fotoPerfil = (ImageView) findViewById(R.id.imgPerfilEditarUsuario);

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
                Usuario userChec = new Usuario(nombre.getText().toString(), apellido.getText().toString(), nombreUsuario.getText().toString(), user.getContraseña(),correo.getText().toString(), telefono.getText().toString(), user.getImagen());
                for (Usuario use : listaUsuarios){
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(userChec);
                    realm.commitTransaction();
                }

                Intent intent = new Intent(EditarUsuario.this,PaginaPrincipal.class);
                intent.putExtra("UserId",user.getId());
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paginaPrincipal =  new Intent(EditarUsuario.this,PaginaPrincipal.class);
                paginaPrincipal.putExtra("userId",usuarioId);
                startActivity(paginaPrincipal);
            }
        });

    }
}