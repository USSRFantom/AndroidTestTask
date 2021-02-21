package ussrfantom.com.example.androidtesttask;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ussrfantom.com.example.androidtesttask.adapters.UserAdapter;
import ussrfantom.com.example.androidtesttask.pojo.Datum;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String name;
    private String id;
    private String country;
    private String lat;
    private String lon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle arguments = getIntent().getExtras();

        if (arguments!=null){
            System.out.println("ДАННЫЕ ПОЛУЧЕНЫ!");
            name = arguments.get("name").toString();
            id = arguments.get("id").toString();
            country = arguments.get("country").toString();
            lat = arguments.get("lat").toString();
            lon = arguments.get("lon").toString();


            System.out.println(name);
            System.out.println(id);
            System.out.println(country);
            System.out.println(lat);
            System.out.println(lon);

        }else{
            System.out.println("ДАННЫЕ НЕ ПОЛУЧЕНЫ!");
        }


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
        LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
        mMap.addMarker(new MarkerOptions().position(latLng).title(name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18), 5000, null);
    }
}