package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Usuario;

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

        nombre = (TextView) findViewById(R.id.txtNombreEditarUsuario);
        apellido = (TextView) findViewById(R.id.txtApellidoEditarUsuario);
        nombreUsuario = (TextView) findViewById(R.id.txtNombreUsuarioEditarUsuario);
        correo = (TextView) findViewById(R.id.txtCorreoEditarUsuario);
        telefono = (TextView) findViewById(R.id.txtPhoneEditarUsuario);

        confirmar = (Button) findViewById(R.id.btnConfirmarEditarUsuario);
        Bundle bundle = getIntent().getExtras();
        String usuarioId = bundle.getString("idEditarUsuario");
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
                Usuario userChec = new Usuario(nombre.getText().toString(), apellido.getText().toString(), nombreUsuario.getText().toString(), user.getContraseña(),correo.getText().toString(), telefono.getText().toString(), Integer.parseInt(fotoPerfil.getResources().toString()));
                for (Usuario use : listaUsuarios){
                    if (use.getCorreo().equals(userChec.getCorreo()) || use.getTelefono().equals(userChec.getTelefono()) || use.getUsername().equals(userChec.getUsername()) && !use.getContraseña().equals(userChec.getContraseña())){
                        Toast.makeText(EditarUsuario.this, "Los datos inroducidos no son validos", Toast.LENGTH_SHORT).show();

                    }else{
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(userChec);
                        realm.commitTransaction();
                    }
                }

            }
        });




    }
}