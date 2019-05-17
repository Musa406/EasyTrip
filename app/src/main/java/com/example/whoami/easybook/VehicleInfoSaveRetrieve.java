package com.example.whoami.easybook;

public class VehicleInfoSaveRetrieve {

    private String numberPlate,chassisNumber,carryingCapacity,weightCapacity,vehicleType,vehicleOwnerUID,source,destination,driverUID,tripID;
    private Boolean confirmed,ontheway;

    public VehicleInfoSaveRetrieve(){

    }

    public VehicleInfoSaveRetrieve(String numberPlate, String chassisNumber,String carryingCapacity,String weightCapacity, String vehicleType, String vehicleOwnerUID, String source, String destination,boolean confirmed,boolean ontheway,String driverUID,String tripID) {
        this.numberPlate = numberPlate;
        this.chassisNumber = chassisNumber;
        this.carryingCapacity = carryingCapacity;
        this.weightCapacity = weightCapacity;
        this.vehicleType = vehicleType;
        this.vehicleOwnerUID = vehicleOwnerUID;
        this.source = source;
        this.destination = destination;
        this.confirmed = confirmed;
        this.ontheway = ontheway;
        this.driverUID = driverUID;
        this.tripID = tripID;

    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getOntheway() {
        return ontheway;
    }

    public void setOntheway(Boolean ontheway) {
        this.ontheway = ontheway;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(String weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public String getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(String carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverUID() {
        return driverUID;
    }

    public void setDriverUID(String driverUID) {
        this.driverUID = driverUID;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }


    public String getVehicleOwnerUID() {
        return vehicleOwnerUID;
    }

    public void setVehicleOwnerUID(String vehicleOwnerUID) {
        this.vehicleOwnerUID = vehicleOwnerUID;
    }

}
