package com.mikelalvarez.a4sharedcars.activites;

import androidx.appcompat.app.AppCompatActivity;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import io.realm.Realm;

public class Registro extends AppCompatActivity {


    EditText txtNombre;
    EditText txtApellido;
    EditText txtContra;
    EditText txtContraConfir;
    EditText txtNombreUsu;
    EditText txtTelefono;
    EditText txtCorreo;
    Button btnAceptar;
    Button btnCancelar;


    ImageView imgIcono;
    Intent inicioSesion;

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        realm = Realm.getDefaultInstance();

        txtApellido = findViewById(R.id.txtApellidoRegistro);
        txtContra = findViewById(R.id.txtContraseña1Registro);
        txtContraConfir = findViewById(R.id.txtContraseña2Registro);
        txtCorreo = findViewById(R.id.txtCorreoRegistro);
        txtTelefono = findViewById(R.id.txtTelefonoRegistro);
        txtNombre = findViewById(R.id.txtNombreRegistro);
        txtNombreUsu = findViewById(R.id.txtNombreUsuarioRegistro);

        imgIcono = findViewById(R.id.imgFotoPerfilRegistro);

        btnAceptar = findViewById(R.id.btnAceptarRegistro);
        btnCancelar = findViewById(R.id.btnCancelarRegistro);

        inicioSesion = new Intent(this,IniciarSesion.class);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(inicioSesion);
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNombre.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "El nombre esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtApellido.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "El apellido esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtNombreUsu.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "El nombre de usuario esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtCorreo.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "El correo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!txtCorreo.getText().toString().contains("@")){
                    Toast.makeText(Registro.this, "Mete un correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtTelefono.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "El Telefono esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtTelefono.getText().length() < 9){
                    Toast.makeText(Registro.this, "El telefono es mas largo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtContra.getText().toString().isEmpty()){
                    Toast.makeText(Registro.this, "La contraseña esta vacia", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtContraConfir.getText() == txtContra.getText()){
                    Toast.makeText(Registro.this, "Tienes que meter la misma contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                realm.beginTransaction();
                Usuario usuario = new Usuario(txtNombre.getText().toString(),txtApellido.getText().toString(),txtNombreUsu.getText().toString(),txtContra.getText().toString(),txtCorreo.getText().toString(),txtTelefono.getText().toString(),(Integer) imgIcono.getTag());
                realm.copyToRealm(usuario);
                realm.commitTransaction();

                startActivity(inicioSesion);
            }
        });

    }
}