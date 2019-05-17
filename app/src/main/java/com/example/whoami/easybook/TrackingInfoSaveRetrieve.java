package com.example.whoami.easybook;

public class TrackingInfoSaveRetrieve {


    private String tripID;
    private double latitude;
    private double longitude;


    public TrackingInfoSaveRetrieve(){

    }

    public TrackingInfoSaveRetrieve(String tripID, double latitude, double longitude) {

        this.tripID = tripID;
        this.latitude = latitude;
        this.longitude = longitude;

    }



    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
