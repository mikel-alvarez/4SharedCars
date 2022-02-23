package com.mikelalvarez.a4sharedcars.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.activites.EditarUsuario;
import com.mikelalvarez.a4sharedcars.activites.GestionarRutas;
import com.mikelalvarez.a4sharedcars.activites.MapsActivity;
import com.mikelalvarez.a4sharedcars.activites.PaginaPrincipal;
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

    Usuario usuarioLogeado;
    OnButtonClick btnGestionClick;
    OnButtonClick btnEditarClick;
    FloatingActionButton btnMapa;

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

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapa =  new Intent(view.getContext(), MapsActivity.class);
                startActivity(mapa);
            }
        });

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

    public void getUsuarioLogeado(Usuario usuario){
        usuarioLogeado = usuario;
        getData(usuarioLogeado, new OnButtonClick() {
            @Override
            public void onItemClick(Usuario user) {
                Intent gestionarRutas = new Intent(view.getContext(), GestionarRutas.class);
                gestionarRutas.putExtra("idUserGestionar", user.getId());
                startActivity(gestionarRutas);

            }
        }, new OnButtonClick() {
            @Override
            public void onItemClick(Usuario user) {
                Intent editarUser = new Intent(view.getContext(), EditarUsuario.class);
                editarUser.putExtra("idEditarUsuario",user.getId());
                startActivity(editarUser);
            }
        });
    }


}