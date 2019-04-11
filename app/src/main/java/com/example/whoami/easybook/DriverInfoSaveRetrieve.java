package com.example.whoami.easybook;

class DriverInfoSaveRetrieve {

    private String email, mobile, NID, password, ownerID, drivingLiscence,name;

    public DriverInfoSaveRetrieve(){

    }

    public DriverInfoSaveRetrieve(String name, String mobile, String email,String drivingLiscence, String NID, String password,  String ownerID) {
        this.email = email;
        this.mobile = mobile;
        this.NID = NID;
        this.password = password;
        this.ownerID = ownerID;
        this.name = name;
        this.drivingLiscence = drivingLiscence;
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

}
