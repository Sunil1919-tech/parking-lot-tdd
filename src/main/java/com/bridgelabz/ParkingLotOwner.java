package com.bridgelabz;

/**
 * purpose: ParkingLotOwner is a class having function to know the capacity is full
 * returns the boolean type if the parking lot is full
 *
 * @author sunil
 * @since 09/11/2021
 */
public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isParkingLotFull;

    /**
     * this method is to check the parkingLot capacity is full
     */
    @Override
    public void capacityIsFull() {
        isParkingLotFull = true;
    }

    /**
     * this method is to check the parking lot capacity is available
     */
    @Override
    public void capacityIsAvailable() {
        isParkingLotFull = false;
    }

    /**
     * purpose: the method checks the parking lot has full whether the capacity exceeded
     * @return boolean type , if the capacity full returns true
     */
    public boolean isCapacityFull() {
        return this.isParkingLotFull;
    }
}
