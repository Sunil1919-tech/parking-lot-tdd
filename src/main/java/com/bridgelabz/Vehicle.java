package com.bridgelabz;

public class Vehicle {

    private final String vehicleName;
    private final String vehicleNum;

    public Vehicle(String vehicleName, String vehicleNum) {
    this.vehicleName = vehicleName;
    this.vehicleNum = vehicleNum;
    }

    public String getVehicleName(){
        return vehicleName;
    }
    public String getVehicleNum(){
        return vehicleNum;
    }
}
