package com.mikelalvarez.a4sharedcars.activites;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikelalvarez.a4sharedcars.R;
import com.mikelalvarez.a4sharedcars.databinding.ActivityMapsBinding;

import io.realm.Realm;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private  MarkerOptions marker;
    Realm realm;

    //todo:Darle una fincionalidad al mapa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        marker = new MarkerOptions();

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(15);
        LatLng cuatrovientos = new LatLng(42.8242834, -1.659874);

        CameraPosition camera = new CameraPosition.Builder()
                .target(cuatrovientos)
                .zoom(18)
                .tilt(38)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(cuatrovientos));


        marker.position(cuatrovientos);
        marker.title("This is Cuatrovientos");
        marker.draggable(true);
        marker.snippet("Caja de texto para introducir datos mas extensos");
        marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on));
        mMap.addMarker(marker);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this,"Click on \n"+
                        "Lat: "+latLng.latitude+"\n"+
                        "Lng: "+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this,"Click long on \n"+
                        "Lat: "+latLng.latitude+"\n"+
                        "Lng: "+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                marker.hideInfoWindow();
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.showInfoWindow();
                Toast.makeText(MapsActivity.this,"Marker Dragged to \n"+
                        "Lat: "+marker.getPosition().latitude+"\n"+
                        "Lng: "+marker.getPosition().longitude, Toast.LENGTH_SHORT).show();

            }
        });
    }
}