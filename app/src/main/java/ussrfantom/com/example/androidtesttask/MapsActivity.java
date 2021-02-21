package ussrfantom.com.example.androidtesttask;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

        }else{
            System.out.println("ДАННЫЕ НЕ ПОЛУЧЕНЫ!");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
        mMap.addMarker(new MarkerOptions().position(latLng).title(name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18), 3000, null);
    }
}