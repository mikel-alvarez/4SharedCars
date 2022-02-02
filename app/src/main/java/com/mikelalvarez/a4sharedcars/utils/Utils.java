package com.mikelalvarez.a4sharedcars.utils;

import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    public static List<Usuario> getUsuarios() {
        return new ArrayList<Usuario>() {{
            add(new Usuario(1, "Pablo", "Fernández", "pablofeerr", "1234", "pablofernandezmoriones@gmail.com", 1000.0, 4, "636674508", R.drawable.hombre, new ArrayList<Integer>(), new ArrayList<Integer>()));
            add(new Usuario(2, "Mikel", "Álvarez", "mikel_alvarez", "1234", "mikelalvarez@gmail.com", 1500.0, 5, "666777888", R.drawable.hombre, new ArrayList<Integer>(), new ArrayList<Integer>()));
            add(new Usuario(3, "Eneko", "Trigo", "elmbo_02", "1234", "eneko_trigo@gmail.com", 500.0, 2, "111222333", R.drawable.nino, new ArrayList<Integer>(), new ArrayList<Integer>()));
            add(new Usuario(4, "Iratxe", "Aldabe", "iratxe_aldabe", "1234", "iratxeeaaldabe@gmail.com", 2000.0, 4, "222333444", R.drawable.mujer, new ArrayList<Integer>(), new ArrayList<Integer>()));
            add(new Usuario(5, "Iban", "Sarría", "_ibansarria_", "1234", "ibansarria@cuatrovientos.org", 750.0, 3, "555666777", R.drawable.silueta, new ArrayList<Integer>(), new ArrayList<Integer>()));
        }};
    }

    public static List<Ruta> getRutas() {
        return new ArrayList<Ruta>() {{
            add(new Ruta(1, new Date(02/02/2022), "08:00", "Burlada - Cuatrovientos", 4.5, 5, 1, new ArrayList<Integer>()));
            add(new Ruta(2, new Date(02/02/2022), "14:45", "Cuatrovientos - Burlada", 4.5, 5, 1, new ArrayList<Integer>()));
            add(new Ruta(3, new Date(02/02/2022), "07:50", "Mutilva Baja - Cuatrovientos", 10,  2, 3, new ArrayList<Integer>()));
            add(new Ruta(4, new Date(02/02/2022), "14:30", "Cuatrovientos - Mutilva", 10, 2, 3, new ArrayList<Integer>()));
            add(new Ruta(5, new Date(03/02/2022), "08:10", "Ansoain - Cuatrovientos", 1.5, 3, 2, new ArrayList<Integer>()));
            add(new Ruta(6, new Date(03/02/2022), "14:40", "Cuatrovientos - Ansoain",1.5, 3, 2, new ArrayList<Integer>()));
            add(new Ruta(7, new Date(03/02/2022), "08:15", "Barañain - Cuatrovientos",3, 1, 4, new ArrayList<Integer>()));
            add(new Ruta(8, new Date(03/02/2022), "15:00", "Cuatrovientos - Barañain",3, 1, 4, new ArrayList<Integer>()));
            add(new Ruta(9, new Date(04/02/2022), "08:00", "Mendillorri - Cuatrovientos",6, 4, 5, new ArrayList<Integer>()));
            add(new Ruta(10, new Date(04/02/2022), "14:35", "Cuatrovientos - Mnedillorri",6, 4, 5, new ArrayList<Integer>()));
        }};
    }

    public double puntosCO2(int plazas, double kms)
    {
        //PLAZAS OCUPADAS * KMS
    }

    public Integer mediaEstrellas()
}
