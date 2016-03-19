package com.disperz.codeforindia.disperz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
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

        LatLng chLocation = new LatLng(28, 77);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chLocation, 9));

        // Add a marker in Sydney and move the camera
        LatLng rash = new LatLng(28.6143, 77.1998);
        mMap.addMarker(new MarkerOptions().position(rash).title("Rashtrapati Bhavan")).showInfoWindow();

        LatLng akshar = new LatLng(28.6125, 77.2272);
        mMap.addMarker(new MarkerOptions().position(akshar).title("Akshardham"));

        LatLng redfort = new LatLng(28.6560, 77.2410);
        mMap.addMarker(new MarkerOptions().position(redfort).title("Redfort"));

        LatLng lotus = new LatLng(28.5533, 77.2586);
        mMap.addMarker(new MarkerOptions().position(lotus).title("Lotus Temple"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if (arg0.getTitle().equals("Rashtrapati Bhavan")) // if marker source is clicked
                {
                    Intent intent = new Intent(MapsActivity.this, GetTicket.class);
                    intent.putExtra("PLACE", "Rashtrapati Bhavan");
                    startActivity(intent);
                }
                if (arg0.getTitle().equals("Akshardham")) // if marker source is clicked
                {
                    Intent intent = new Intent(MapsActivity.this, GetTicket.class);
                    intent.putExtra("PLACE", "Akshardham");
                    startActivity(intent);
                }

                if (arg0.getTitle().equals("Lotus Temple")) // if marker source is clicked
                {
                    Intent intent = new Intent(MapsActivity.this, GetTicket.class);
                    intent.putExtra("PLACE", "Lotus Temple");
                    startActivity(intent);
                }

                if (arg0.getTitle().equals("Rashtrapati Bhavan")) // if marker source is clicked
                {
                    Intent intent = new Intent(MapsActivity.this, GetTicket.class);
                    intent.putExtra("PLACE", "Rashtrapati Bhavan");
                    startActivity(intent);
                }

                return true;
            }

        });



    }

}
