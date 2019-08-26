package com.example.googlemap.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.googlemap.DisplayInformationActivity;
import com.example.googlemap.R;
import com.example.googlemap.adapter.CustomInfoWindowGoogleMapAdapter;
import com.example.googlemap.model.School;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener, GoogleMap.OnInfoWindowClickListener {

    private Location mLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private GoogleMap mMap = null;
    private LatLng latLng;
    private int radiusInMeters = 5000;
    private ArrayList<School> schoolArrayList;
    private int size;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_map, container, false);

        schoolArrayList = (ArrayList<School>) getArguments().getSerializable("arrayList");
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getCurrentLocation();

    }


    @Override
    public void onInfoWindowClick(Marker marker) {

        School school = (School) marker.getTag();
        Intent intent = new Intent(getActivity(),DisplayInformationActivity.class);
        intent.putExtra("item", (Parcelable) school);
        startActivity(intent);
    }

    @Override
    public void onMyLocationChange(Location location) {
        School school;
        LatLng latLngTemp;
        MarkerOptions markerOptions;

        Location target = new Location("target");

        size = schoolArrayList.size();

        for (int i = 0; i < size; i++) {

            school = schoolArrayList.get(i);

            latLngTemp = new LatLng(school.getLatitude(), school.getLongitude());

            target.setLatitude(school.getLatitude());
            target.setLongitude(school.getLongitude());

            if (location.distanceTo(target) < radiusInMeters) {

                markerOptions = new MarkerOptions().position(latLngTemp);

            } else {

                markerOptions = new MarkerOptions().position(latLngTemp)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            }


            Marker marker = mMap.addMarker(markerOptions);
            marker.setTag(school);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnInfoWindowClickListener(this);
        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());

        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                // radius in meters
                .radius(radiusInMeters)
                .strokeWidth(2)
                .strokeColor(Color.BLUE);

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(this);

        CustomInfoWindowGoogleMapAdapter customInfoWindowGoogleMapAdapter = new CustomInfoWindowGoogleMapAdapter(getActivity());
        mMap.setInfoWindowAdapter(customInfoWindowGoogleMapAdapter);

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        mMap.addCircle(circleOptions);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLocation = location;
                    mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment = SupportMapFragment.newInstance();

                    getFragmentManager().beginTransaction().replace(R.id.frame_container,mapFragment).commit();
                    mapFragment.getMapAsync(GoogleMapFragment.this);

                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
        }
    }
}
