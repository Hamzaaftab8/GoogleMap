package com.example.googlemap.model;

import android.os.Parcel;
import android.os.Parcelable;


public class School implements Parcelable {

    double latitude,longitude;
    String locationName;

    protected School(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        locationName = in.readString();
        id = in.readInt();
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    public int getSchoolId() {
        return id;
    }

    int id;

    public School(double latitude, double longitude, String name,int id){
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationName = name;
        this.id = id;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocationName() {
        return locationName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(locationName);
        parcel.writeInt(id);
    }
}
