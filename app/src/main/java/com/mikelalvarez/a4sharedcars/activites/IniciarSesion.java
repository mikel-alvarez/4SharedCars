package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;
import com.mikelalvarez.a4sharedcars.utils.Utils;

import io.realm.Realm;
import io.realm.RealmResults;

public class IniciarSesion extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtContrasena;
    Button btnIniciarSesion;
    Button btnRegistrar;
    Button btnOlvidado;
    Intent regintro;
    Intent paginaPrincipal;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        txtContrasena = findViewById(R.id.txtContrasenaInicioSesion);
        txtUsuario = findViewById(R.id.txtNombreUsuarioInicioSesion);
        btnIniciarSesion = findViewById(R.id.btnInciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistroIniciarSesion);
        btnOlvidado = findViewById(R.id.btnOlvidoContrasenaIniciarSesion);

        regintro = new Intent(this,Registro.class);
        paginaPrincipal = new Intent(this,PaginaPrincipal.class);

        realm = Realm.getDefaultInstance();

        if (realm.where(Usuario.class).findAll().isEmpty()){
            Utils.inicializarBaseDatosUsuario();
        }
        if (realm.where(Ruta.class).findAll().isEmpty()){
            Utils.inicializarBaseDatosRuta();
        }

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtContrasena.getText().toString().isEmpty()){
                    Toast.makeText(IniciarSesion.this,"La contraseña esta vacia", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtUsuario.getText().toString().isEmpty()){
                    Toast.makeText(IniciarSesion.this, "El usuario esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                Usuario usuario = realm.where(Usuario.class).equalTo("username",txtUsuario.getText().toString()).findFirst();

                if (usuario == null){
                    Toast.makeText(IniciarSesion.this, "Usuario incorrecta", Toast.LENGTH_SHORT).show();
                    return;
                }

               if (!usuario.getContraseña().equals(txtContrasena.getText().toString())){
                  Toast.makeText(IniciarSesion.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                   return;
              }



              paginaPrincipal.putExtra("UserId",usuario.getId());
              startActivity(paginaPrincipal);
            }
        });
        
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(regintro);
            }
        });
        
        btnOlvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IniciarSesion.this, "Consulta al administrador", Toast.LENGTH_SHORT).show();
            }
        });

    }
}