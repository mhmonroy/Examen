package com.example.hmonroy.myapplication;

import android.*;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import DBManager.DBManager;

public class ResumenActivity extends AppCompatActivity {

    DBManager dbManager = new DBManager(this);

    private GoogleMap mapa;
    public MapFragment mapFragment;

    public TextView nombre;
    public TextView correo;
    public TextView direccion;

    private Double lat = 0.0;
    private Double lon = 0.0;

    public String sNombre;
    public String sCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        String id = getIntent().getStringExtra("id");

        nombre = (TextView) findViewById(R.id.lblNombre);
        correo = (TextView) findViewById(R.id.lblMail);
        direccion = (TextView) findViewById(R.id.lblDir);

        Cursor cursor = dbManager.empleado(id);

        cursor.moveToFirst();

        nombre.setText(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
        correo.setText(cursor.getString(4));
        direccion.setText(cursor.getString(5) + " " + cursor.getString(6) + " " + cursor.getString(7) + " " + cursor.getString(8) + " " + cursor.getString(9) + " " + cursor.getString(10));

        sCorreo = cursor.getString(4);
        sNombre = cursor.getString(1);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                mapa = googleMap;

                if (ActivityCompat.checkSelfPermission(ResumenActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ResumenActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("App", "Sin permisos");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ResumenActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                    } else {
                        ActivityCompat.requestPermissions(ResumenActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    }

                    return;
                } else {
                    Log.d("App", "permisos");
                    mapa.setMyLocationEnabled(true);
                }

                //Location location =  mapa.getMyLocation();
                LatLng longitud = new LatLng(19.401968, -99.170484);
                //LatLng longitud = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(longitud, 16);
                googleMap.animateCamera(cameraUpdate);

                googleMap.addMarker(new MarkerOptions().position(longitud).icon(BitmapDescriptorFactory.defaultMarker()).title(sNombre).snippet(sCorreo));


                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        LatLng latLng1 = marker.getPosition();
                        return false;
                    }
                });

                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker()));
                    }
                });

                googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        Log.d("App", "Location: " + String.valueOf(location.getLatitude()));
                    }
                });


            }
        });
    }
}
