package com.example.googlemap.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.googlemap.fragment.GoogleMapFragment;
import com.example.googlemap.fragment.LocationFragment;
import com.example.googlemap.model.School;

import java.util.ArrayList;

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private int noOfItems;
    private ArrayList<School> schoolArrayList;
    Bundle bundle;

    public TabFragmentAdapter(FragmentManager fm, int noOfItems) {
        super(fm);

        this.noOfItems = noOfItems;
        schoolArrayList = new ArrayList<>();
        fillArrayLocations();

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: {
                bundle = new Bundle();
                bundle.putSerializable("arrayList", schoolArrayList);
                GoogleMapFragment googleMapFragment = new GoogleMapFragment();
                googleMapFragment.setArguments(bundle);
                return googleMapFragment;
            }
            case 1: {
                bundle = new Bundle();
                bundle.putSerializable("arrayList", schoolArrayList);
                LocationFragment locationFragment = new LocationFragment();
                locationFragment.setArguments(bundle);
                return locationFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfItems;
    }

    public void fillArrayLocations() {
        schoolArrayList.add(new School(31.481026, 74.332188, "Roots Internation School",1));
        schoolArrayList.add(new School(31.488326, 74.347906, "Civil Aviation Public School",2));
        schoolArrayList.add(new School(31.491028, 74.368511, "Allied School Walton Campus",3));
        schoolArrayList.add(new School(31.499309, 74.331598, "Hamdard Public School",4));
        schoolArrayList.add(new School(31.505527, 74.352892, "Lahore Grammer School",5));
        schoolArrayList.add(new School(31.516727, 74.344974, "National Grammer School",6));
        schoolArrayList.add(new School(31.474416, 74.281919, "Lahore Lyceum School",7));
        schoolArrayList.add(new School(31.550151, 74.352550, "Lahore American School",8));
        schoolArrayList.add(new School(31.478584, 74.278177, "International School of Choueifat",9));
        schoolArrayList.add(new School(31.544852, 74.325411, "Crescent Model School",10));
    }
}
