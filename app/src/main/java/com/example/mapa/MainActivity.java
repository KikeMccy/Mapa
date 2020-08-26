package com.example.mapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void cambiarTipoMapa(View view){
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
    }

    public void moverCamara(View view)
    {
        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.012848, -79.469665), 16);
        mapa.moveCamera(camUpd1);
    }

    public void marcarPoligono()
    {
        PolylineOptions lineasPoligono = new PolylineOptions()
                .add(new LatLng(-1.01125, -79.46643))
                .add(new LatLng(-1.01125,-79.47243))
                .add(new LatLng(-1.0142,-79.47243))
                .add(new LatLng(-1.0142, -79.46643))
                .add(new LatLng(-1.01125,-79.46643));
        lineasPoligono.width(10);
        lineasPoligono.color(Color.GREEN);
        mapa.addPolyline(lineasPoligono);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa= googleMap;
        mapa.setOnMapClickListener(this);
        marcarPoligono();
        sitiosUTEQ();
    }

    public void sitiosUTEQ(){
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01294, -79.46767)).title("Auditorio"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01291, -79.46786)).title("EMPRESA PUBLICA UTEQ"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01304, -79.46842)).title("Rectorado"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01299, -79.4688)).title("Unidad de Postgrado"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01231, -79.46843)).title("Biblioteca"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01223, -79.46901)).title("Unidad Bienestar Estudiantil"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01291, -79.46933)).title("Facultad Ciencias Agrarias"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01219, -79.46963)).title("Instituto de Informática"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01292, -79.47001)).title("Comedor Universitario"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01215, -79.4701)).title("Facultad Ciencias Empresariales"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.0126, -79.4706)).title("Facultad Ciencias de Ingeniería"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01268, -79.47103)).title("Facultad Ciencias Ambientales"));
        mapa.addMarker(new MarkerOptions().position(new LatLng(-1.01226, -79.47074)).title("Taller UTEQ"));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Projection proj = mapa.getProjection();
        Point coord = proj.toScreenLocation(latLng);
        Toast.makeText(
                MainActivity.this,
                "Click\n" +
                        "Lat: " + latLng.latitude + "\n" +
                        "Lng: " + latLng.longitude + "\n" +
                        "X: " + coord.x + " - Y: " + coord.y,
                Toast.LENGTH_SHORT).show();
    }
}