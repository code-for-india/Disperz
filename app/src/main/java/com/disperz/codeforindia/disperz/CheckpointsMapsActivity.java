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

public class CheckpointsMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkpoints_maps);
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

        Intent intent = getIntent();
        String place = intent.getStringExtra("PLACE");

        setTitle(place);

        LatLng chLocation = new LatLng(28.6143, 77.1998);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chLocation, 17));

        LatLng check1 = new LatLng(28.614456, 77.197835);
        mMap.addMarker(new MarkerOptions().position(check1).title("Mughal Garden"));

        LatLng rash = new LatLng(28.614219, 77.200345);
        mMap.addMarker(new MarkerOptions().position(rash).title("Rashtrapati Bhavan Museum")).showInfoWindow();

        LatLng akshar = new LatLng(28.614342, 77.201957);
        mMap.addMarker(new MarkerOptions().position(akshar).title("Jaipur Column"));

        LatLng redfort = new LatLng(28.614487, 77.199413);
        mMap.addMarker(new MarkerOptions().position(redfort).title("Cultural Hall"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {

                Intent intent = new Intent(CheckpointsMapsActivity.this, GetTicket.class);
                intent.putExtra("PLACE", arg0.getTitle());

                if (arg0.getTitle().equals("Mughal Garden")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 1");
                }
                if (arg0.getTitle().equals("Rashtrapati Bhavan Museum")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 2");
                }

                if (arg0.getTitle().equals("Jaipur Column")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 3");
                }

                if (arg0.getTitle().equals("Cultural Hall")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 4");
                }

                startActivity(intent);
                return true;
            }
        });
    }
}
