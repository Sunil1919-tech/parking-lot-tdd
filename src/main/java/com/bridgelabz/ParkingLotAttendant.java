package com.bridgelabz;

/**
 * purpose:Parking lot attendant has to park the vehicle when capacity is available
 *
 * @author Sunil
 * @since 11/11/2021
 */
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3);

    /**
     * method to park the vehicle by attendant when capacity available
     *
     * @param vehicle the object which is going to park by Attendant
     * @throws ParkingLotException throwable exception where there is no vehicle to park
     */
    public void attendantParked(Object vehicle) throws ParkingLotException {
        parkingLotSystem.park(vehicle);
    }

    /**
     * method to Unpark the vehicle by the Attendant
     *
     * @param vehicle the object which is going to Unpark by Attendant
     * @throws ParkingLotException throwable exception where there is no vehicle to UnPark
     */
    public void attendantUnParked(Object vehicle) throws ParkingLotException {
        parkingLotSystem.unPark(vehicle);
    }
}
