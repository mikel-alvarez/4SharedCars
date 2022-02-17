package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EditarUsuario extends AppCompatActivity {
    ImageView fotoPerfil;
    TextView nombre;
    TextView apellido;
    TextView nombreUsuario;
    TextView correo;
    TextView telefono;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fotoPerfil = (ImageView) findViewById(R.id.imgPerfilEditarUsuario);

        nombre = (TextView) findViewById(R.id.txtNombreEditarUsuario);
        apellido = (TextView) findViewById(R.id.txtApellidoEditarUsuario);
        nombreUsuario = (TextView) findViewById(R.id.txtNombreUsuarioEditarUsuario);
        correo = (TextView) findViewById(R.id.txtCorreoEditarUsuario);
        telefono = (TextView) findViewById(R.id.txtPhoneEditarUsuario);

        Bundle bundle = getIntent().getExtras();
        String usuarioId = bundle.getString("idEditarUsuario");




    }
}