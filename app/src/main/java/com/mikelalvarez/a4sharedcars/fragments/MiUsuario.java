package com.mikelalvarez.a4sharedcars.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Usuario;


public class MiUsuario extends Fragment {

    View view;
    TextView lblNombre;
    TextView lblApellido;
    TextView lblNombreUsuario;
    TextView lblCorreo;
    TextView lblTelefono;
    TextView lblPunto;
    ImageView imgAvatar;
    Button btnGestion;
    Button  btnEditar;


    public MiUsuario() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_usuario, container, false);

        lblApellido = view.findViewById(R.id.txtNombreUsuario);
        lblNombre = view.findViewById(R.id.txtApellidoUsuario);
        lblCorreo = view.findViewById(R.id.txtCorreoUsuario);
        lblNombreUsuario = view.findViewById(R.id.txtNombreUsuarioDeUsuario);
        lblTelefono = view.findViewById(R.id.txtTelefonoUsuario);
        lblPunto = view.findViewById(R.id.txtPuntosCo2Usuario);
        imgAvatar = view.findViewById(R.id.imgPerfilUsuario);

        btnEditar = view.findViewById(R.id.btnEditarUsuario);
        btnGestion = view.findViewById(R.id.btnGestionarRutas);

        return view;
    }

    public void getData(Usuario user, OnButtonClick btnGestionClick,OnButtonClick btnEditarClick){

        lblNombre.setText(user.getNombre());
        lblApellido.setText(user.getApellido());
        lblNombreUsuario.setText(user.getUsername());
        lblCorreo.setText(user.getCorreo());
        lblTelefono.setText(user.getTelefono());
        lblPunto.setText(user.getPuntosC02().toString());

        imgAvatar.setImageResource(user.getImagen());

        btnGestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGestionClick.onItemClick(user);
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEditarClick.onItemClick(user);
            }
        });
    }

    public interface OnButtonClick{
        void onItemClick(Usuario user);
    }
}