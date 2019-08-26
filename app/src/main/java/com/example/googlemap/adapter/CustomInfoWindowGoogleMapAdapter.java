package com.example.googlemap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.googlemap.R;
import com.example.googlemap.model.School;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMapAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public CustomInfoWindowGoogleMapAdapter(Context context) {

        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView locationName, locationLat, locationLong,tvId;

        View view = layoutInflater.inflate(R.layout.info_window_items, null);

        locationName = view.findViewById(R.id.tv_name);
        locationLat = view.findViewById(R.id.tv_lat);
        locationLong = view.findViewById(R.id.tv_long);
        tvId = view.findViewById(R.id.tv_id);

        School school = (School) marker.getTag();

        tvId.setText(String.valueOf(school.getSchoolId()));
        locationName.setText(school.getLocationName());
        locationLat.setText(String.valueOf(school.getLatitude()));
        locationLong.setText(String.valueOf(school.getLongitude()));

        return view;
    }
}
