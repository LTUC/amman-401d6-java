package com.example.weatherme.ui.details;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.geo.maplibre.view.AmplifyMapView;
import com.amplifyframework.geo.models.MapStyle;
import com.example.weatherme.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

import java.util.List;

public class GeoActivity extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;

    private int PERMISSION_ID = 44;

    private double latitude;
    private double longitude;

    private AmplifyMapView amplifyMapView;

    List<MapStyle> styles;

    private final LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();

            amplifyMapView.getMapView().getStyle((map, style) -> {
                LatLng spaceNeedle = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                amplifyMapView.getMapView().symbolManager.create(
                        new SymbolOptions()
                                .withIconImage("place")
                                .withLatLng(spaceNeedle)
                );
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(spaceNeedle, 16.0));
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        amplifyMapView = findViewById(R.id.mapView);
        amplifyMapView.setOnPlaceSelectListener((place, symbol) -> {
            // place is an instance of AmazonLocationPlace
            // symbol is an instance of Symbol from MapLibre
            Log.i("MyAmplifyApp", "The selected place is " + place.getLabel());
            Log.i("MyAmplifyApp", "It is located at " + place.getCoordinates());
        });

        Amplify.Geo.getAvailableMaps(
                result -> {
                    for (final MapStyle style : result) {
                        Log.i("MyAmplifyApp", style.toString());
                        styles.add(style);
                    }

                    amplifyMapView.getMapView().setStyle(styles.get(0), style -> {
                        Log.i("MyAmplifyApp", "Finished loading " + styles.get(0).getStyle());
                    });
                },
                error -> Log.e("MyAmplifyApp", "Failed to get available maps.", error)
        );

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // method to get the location
        getLastLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            amplifyMapView.getMapView().getStyle((map, style) -> {
                                LatLng spaceNeedle = new LatLng(latitude, longitude);
                                amplifyMapView.getMapView().symbolManager.create(
                                        new SymbolOptions()
                                                .withIconImage("place")
                                                .withLatLng(spaceNeedle)
                                );
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(spaceNeedle, 16.0));
                            });
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }

    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat
                        .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }
}