package com.example.googlemap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.googlemap.R;
import com.example.googlemap.model.School;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<School> {

    Context context;
    int resourceId;
    ArrayList<School> schoolArrayList;
    LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, int resource, ArrayList<School> arrayList) {
        super(context,resource,arrayList);

        this.context = context;
        this.resourceId = resource;
        this.schoolArrayList = arrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView locationName, locationLat, locationLong,tvId;

        convertView = layoutInflater.inflate(resourceId,null);

        locationName = convertView.findViewById(R.id.tv_name);
        locationLat = convertView.findViewById(R.id.tv_lat);
        locationLong = convertView.findViewById(R.id.tv_long);
        tvId = convertView.findViewById(R.id.tv_id);

        School school = schoolArrayList.get(position);

        locationName.setText(school.getLocationName());
        locationLat.setText(String.valueOf(school.getLatitude()));
        locationLong.setText(String.valueOf(school.getLongitude()));
        tvId.setText(String.valueOf(school.getSchoolId()));

        return convertView;
    }
}
