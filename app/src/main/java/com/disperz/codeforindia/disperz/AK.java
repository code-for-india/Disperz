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

public class AK extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ak);
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


        LatLng chLocation = new LatLng(28.612706, 77.277546);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chLocation, 17));

        LatLng check1 = new LatLng(28.612706, 77.277546);
        mMap.addMarker(new MarkerOptions().position(check1).title("Mandir"));

        LatLng rash = new LatLng(28.613781, 77.277174);
        mMap.addMarker(new MarkerOptions().position(rash).title("Hirdya Kamal")).showInfoWindow();

        LatLng akshar = new LatLng(28.613877, 77.279382);
        mMap.addMarker(new MarkerOptions().position(akshar).title("Gandhi Statue"));

        LatLng redfort = new LatLng(28.614041, 77.280853);
        mMap.addMarker(new MarkerOptions().position(redfort).title("Freedom Fighters"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {

                Intent intent = new Intent(AK.this, GetTicket.class);
                intent.putExtra("PLACE", arg0.getTitle());

                if (arg0.getTitle().equals("Mandir")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 1");
                }
                if (arg0.getTitle().equals("Hirdya Kamal")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 2");
                }

                if (arg0.getTitle().equals("Gandhi Statue")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 3");
                }

                if (arg0.getTitle().equals("Freedom Fighters")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 4");
                }

                startActivity(intent);
                return true;
            }
        });
    }
}
