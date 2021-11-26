package com.bridgelabz;

/************************************************************************************
 * purpose: Vehicle class declaring the variables for variables to check vehicle name
 * and  vehicle number
 *
 * @author Sunil
 * @since 09/11/2021
 *************************************************************************************/
public class Vehicle {
    private final String vehicleName;
    private final String vehicleNum;
    private final String color;

    enum VehicleType{
        SMALL,MEDIUM,LARGE;
    }
    /**
     * purpose: Constructor initializing the vehicle parameters
     * @param vehicleName to check the vehicle Name in the parked vehicle
     * @param vehicleNum to check the vehicle Number from the parking lot
     * @param color: the color of the vehicle in the parking lot
     */
    public Vehicle(String vehicleName, String vehicleNum, String color, VehicleType vehicleType) {
    this.vehicleName = vehicleName;
    this.vehicleNum = vehicleNum;
    this.color =color;
    }

    public String getVehicleName(){
        return vehicleName;
    }
    public String getVehicleNum(){
        return vehicleNum;
    }
    public String getColor(){
        return color;
    }
}
