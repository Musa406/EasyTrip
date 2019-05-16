package com.example.whoami.easybook;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.whoami.easybook.R;
import com.example.whoami.easybook.TrackingInfoSaveRetrieve;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private ChildEventListener mChildEventListener;
    private DatabaseReference mUsers;
    Marker marker;
    String tripID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        tripID = intent.getStringExtra("tripID");
        Toast.makeText(getApplicationContext(),tripID,Toast.LENGTH_SHORT).show();


// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ChildEventListener mChildEventListener;
        mUsers= FirebaseDatabase.getInstance().getReference("TrackLocation");
        mUsers.push().setValue(marker);

        Button finishButton = (Button) findViewById(R.id.finish_btn);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnMarkerClickListener(this);
       // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()){
                    TrackingInfoSaveRetrieve user = s.getValue(TrackingInfoSaveRetrieve.class);

                    if(user.getTripID().equals(tripID)){
                        LatLng location=new LatLng(user.getLatitude(),user.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(location).title("Vehicle")).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, (float) 16.0));

                        mMap.addCircle(
                                new CircleOptions()
                                        .center(location)
                                        .radius(500)
                                        .strokeWidth(2f)
                                        .strokeColor(Color.RED)
                                        .fillColor(Color.argb(70,150,50,50))

                        );
                        mMap.getUiSettings().setZoomControlsEnabled(true);
                        mMap.getUiSettings().setRotateGesturesEnabled(true);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}






























//package com.example.whoami.easybook;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CircleOptions;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//
//    private LocationListener locationListener;
//    private LocationManager locationManager;
//
//    private final long MIN_TIME= 1000;
//    private final long MIN_DISTANCE= 10;
//    private  double latitude, longitude;
//    private LatLng latlang;
//    String tripID;
//    Query dbTracking;
//   // private DatabaseReference dbTracking;
//
//    private Button finishButton;
//    private String tripIdStr="jj";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//
//
//
//        Intent intent = getIntent();
//        tripID = intent.getStringExtra("tripID");
//        Toast.makeText(getApplicationContext(),tripID,Toast.LENGTH_SHORT).show();
//
//
//        dbTracking = FirebaseDatabase.getInstance().getReference("TrackLocation")
//                .orderByChild("tripID")
//                .equalTo(tripID);
//
//
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
//
//        finishButton = (Button)  findViewById(R.id.finish_btn);
//
//        finishButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // FirebaseAuth.getInstance().signOut();
////
//                finish();
//            }
//        });
//    }
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//                        latlang = new LatLng(latitude,longitude);
//                        mMap.addMarker(new MarkerOptions().position(latlang).title("Driver Location"));
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlang, (float) 10.0));
//
//                        mMap.addCircle(
//                                new CircleOptions()
//                                        .center(latlang)
//                                        .radius(500)
//                                        .strokeWidth(2f)
//                                        .strokeColor(Color.RED)
//                                        .fillColor(Color.argb(70,150,50,50))
//
//                        );
//
//                       // mMap.setMyLocationEnabled(true);
//                        mMap.getUiSettings().setZoomControlsEnabled(true);
//                        mMap.getUiSettings().setRotateGesturesEnabled(true);
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        dbTracking.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot tripsnapshot: dataSnapshot.getChildren()) {
//                    TrackingInfoSaveRetrieve trakingInfo = tripsnapshot.getValue(TrackingInfoSaveRetrieve.class);
//
//                    //Toast.makeText(getApplicationContext(),"Hi "+latitude+longitude,Toast.LENGTH_SHORT).show();
//                    if(trakingInfo.getTripID().equals(tripID)){
//                        latitude = trakingInfo.getLatitude();
//                        longitude = trakingInfo.getLongitude();
//
//                        Toast.makeText(getApplicationContext(),"Hi "+latitude+longitude,Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }
//}
