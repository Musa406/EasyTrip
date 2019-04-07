package com.example.whoami.easybook;

class DriverInfoSaveRetrieve {

    private String email, mobile, NID, password, userID, drivingLiscence,name;

    public DriverInfoSaveRetrieve(){

    }

    public DriverInfoSaveRetrieve(String name, String mobile, String email,String drivingLiscence, String NID, String password,  String userID) {
        this.email = email;
        this.mobile = mobile;
        this.NID = NID;
        this.password = password;
        this.userID = userID;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDrivingLiscence() {
        return drivingLiscence;
    }

    public void setDrivingLiscence(String drivingLiscence) {
        this.drivingLiscence = drivingLiscence;
    }

}
