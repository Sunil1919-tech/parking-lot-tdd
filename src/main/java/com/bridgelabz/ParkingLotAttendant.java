package com.bridgelabz;

/**
 * purpose: Parking lot attendant has to park the vehicle when capacity is available
 *
 * @author Sunil
 * @since 11/11/2021
 */
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * purpose: method to park the vehicle by attendant when capacity available
     *
     * @param vehicle the Vehicle which is going to park by Attendant
     * @throws ParkingLotException throwable exception where there is no vehicle to park
     */
    public void attendantParked(Vehicle vehicle){
        parkingLotSystem.park(vehicle);
    }
}
