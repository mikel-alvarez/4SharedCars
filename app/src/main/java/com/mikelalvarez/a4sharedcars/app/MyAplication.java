package com.mikelalvarez.a4sharedcars.app;

import android.app.Application;

import com.mikelalvarez.a4sharedcars.model.Ruta;
import com.mikelalvarez.a4sharedcars.model.Usuario;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyAplication extends Application {
    public static AtomicInteger idRuta = new AtomicInteger();
    public static AtomicInteger idUsuario = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        idRuta = getIdByTable(realm, Ruta.class);
        idUsuario = getIdByTable(realm, Usuario.class);
        realm.close();

    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyclass) {
        RealmResults<T> results = realm.where(anyclass).findAll();

        if (results.size()>0){
            return new AtomicInteger(results.max("id").intValue());

        }else {
            return new AtomicInteger(0);
        }
    }

    private void setUpRealmConfig() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
