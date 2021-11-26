package com.bridgelabz;

/**
 * purpose: AirportSecurity is class that acts as observer for parking lot system
 *
 * @author Sunil
 * @since 11/11/2021
 */
public class AirportSecurity implements ParkingLotObserver {
    private boolean isParkingLotFull;

    /**
     * this method is to check the parkingLot capacity is full
     */
    @Override
    public void capacityIsFull() {
        isParkingLotFull = true;
    }

    /**
     *purpose: this method is to check the parking lot capacity is available
     */
    @Override
    public void capacityIsAvailable() {
        isParkingLotFull = false;
    }

    /**
     * purpose: this returns the boolean to check status whether parking lot is full
     *
     * @return parking lot full or not
     */
    public boolean isCapacityFull() {
        return this.isParkingLotFull;
    }
}
