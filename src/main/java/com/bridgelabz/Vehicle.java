package com.bridgelabz;

public class Vehicle {

    /************************************************************************************
     * Vehicle class declaring the variables for variables to check vehicle name
     * and  vehicle number
     *
     * @author Sunil
     * @since 09/11/2021
     *************************************************************************************/
    private final String vehicleName;
    private final String vehicleNum;

    /**
     * Constructor initializing the Vehicle name and Number
     * @param vehicleName to check the vehicle Name in the parked vehicle
     * @param vehicleNum to check the vehicle Number from the parking lot
     */
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
