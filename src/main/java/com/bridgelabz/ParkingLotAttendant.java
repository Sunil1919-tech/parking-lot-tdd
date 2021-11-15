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
     * @param vehicle
     * @throws ParkingLotException
     */
    public void attendantParked(Object vehicle) throws ParkingLotException {
        parkingLotSystem.park(vehicle);
    }
}
