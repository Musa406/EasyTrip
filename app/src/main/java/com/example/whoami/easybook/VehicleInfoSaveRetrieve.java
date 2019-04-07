package com.example.whoami.easybook;

public class VehicleInfoSaveRetrieve {

    private String numberPlate,chassisNumber,carryingCapacity,weightCapacity,vehicleType;

    public VehicleInfoSaveRetrieve(){

    }

    public VehicleInfoSaveRetrieve(String numberPlate, String chassisNumber,String carryingCapacity,String weightCapacity, String vehicleType) {
        this.numberPlate = numberPlate;
        this.chassisNumber = chassisNumber;
        this.carryingCapacity = carryingCapacity;
        this.weightCapacity = weightCapacity;
        this.vehicleType = vehicleType;
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
}
