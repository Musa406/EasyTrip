package com.example.whoami.easybook;

class DriverInfoSaveRetrieve {

    private String email, mobile, NID, password, ownerID, drivingLiscence,name,available,driverID,tripID;

    public DriverInfoSaveRetrieve(){

    }

    public DriverInfoSaveRetrieve(String name, String mobile, String email,String drivingLiscence, String NID, String password,  String ownerID, String available,String driverID,String tripID) {
        this.email = email;
        this.mobile = mobile;
        this.NID = NID;
        this.password = password;
        this.ownerID = ownerID;
        this.name = name;
        this.drivingLiscence = drivingLiscence;
        this.available = available; //yes or no
        this.driverID = driverID;
        this.tripID = tripID;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getDrivingLiscence() {
        return drivingLiscence;
    }

    public void setDrivingLiscence(String drivingLiscence) {
        this.drivingLiscence = drivingLiscence;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
