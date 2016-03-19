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

public class RF extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lt);
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

        LatLng chLocation = new LatLng(28.655816, 77.241009);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chLocation, 17));

        LatLng check1 = new LatLng(28.655816, 77.241009);
        mMap.addMarker(new MarkerOptions().position(check1).title("Naqquar Khaana"));

        LatLng rash = new LatLng(28.655670, 77.242270);
        mMap.addMarker(new MarkerOptions().position(rash).title("Diwan-i-Aam")).showInfoWindow();

        LatLng akshar = new LatLng(28.655744, 77.243542);
        mMap.addMarker(new MarkerOptions().position(akshar).title("Rang Mahal"));

        LatLng redfort = new LatLng(28.656407, 77.243586);
        mMap.addMarker(new MarkerOptions().position(redfort).title("Diwan-i-Khas"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {

                Intent intent = new Intent(RF.this, GetTicket.class);
                intent.putExtra("PLACE", arg0.getTitle());

                if (arg0.getTitle().equals("Naqquar Khaana")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 1");
                }
                if (arg0.getTitle().equals("Diwan-i-Aam")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 2");
                }

                if (arg0.getTitle().equals("Rang Mahal")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 3");
                }

                if (arg0.getTitle().equals("Diwan-i-Khas")) // if marker source is clicked
                {
                    intent.putExtra("GATE", "Gate 4");
                }

                startActivity(intent);
                return true;
            }
        });
    }
}
