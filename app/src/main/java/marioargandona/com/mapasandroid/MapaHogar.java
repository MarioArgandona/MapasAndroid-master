package marioargandona.com.mapasandroid;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaHogar extends FragmentActivity implements OnMapReadyCallback , View.OnClickListener {

    private GoogleMap mMap;
    private String latitud;
    private String longitud;
    private String descripcion;
    private Button btnRegresar;
    private Integer tipoImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_hogar);

        btnRegresar = (Button)findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            latitud = extras.getString("latitud");
            if(latitud == null) {
                Toast.makeText(this, "La latitud se encuentra vacía.", Toast.LENGTH_SHORT).show();
                return;
            }

            longitud = extras.getString("longitud");
            if(longitud == null)
            {
                Toast.makeText(this, "La longitud se encuentra vacía.", Toast.LENGTH_SHORT).show();
                return;
            }

            descripcion = extras.getString("descripcion");
            if(descripcion == null)
            {
                Toast.makeText(this, "La descripción se encuentra vacía.", Toast.LENGTH_SHORT).show();
                return;
            }

            tipoImagen = extras.getInt("icono");
            if(tipoImagen == null)
            {
                Toast.makeText(this, "La imagen no se pudo procesar.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

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

        // Add a marker in Sydney and move the camera
        LatLng ubicacionIzcalli = new LatLng(Double.valueOf(latitud), Double.valueOf(longitud));
        mMap.addMarker(new MarkerOptions().position(ubicacionIzcalli).title(descripcion)
                        .icon(BitmapDescriptorFactory.fromResource(tipoImagen)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionIzcalli));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(latitud), Double.valueOf(longitud)), 16.0f));
    }


    public void regresar(View v)
    {
        //Intent intent = new Intent(, MainActivity.class);
        //startActivity(intent);

        switch(v.getId())
        {
            case R.id.btnRegresar:
                Intent intent = new Intent(MapaHogar.this , MainActivity.class);
                startActivity(intent);
            break;
        }
    }


    @Override
    public void onClick(View v) {
        if(v.equals(btnRegresar))
        {
            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);
        }
    }




}
