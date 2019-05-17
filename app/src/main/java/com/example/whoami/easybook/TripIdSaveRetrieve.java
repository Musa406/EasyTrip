package com.example.whoami.easybook;

public class TripIdSaveRetrieve {
    private String tripID;

    public TripIdSaveRetrieve(){

    }


    public TripIdSaveRetrieve(String tripID){
        this.tripID = tripID;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }
}
