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

    public void capacityIsFull() {
        isParkingLotFull = true;
    }

    public boolean isCapacityFull() {
        return this.isParkingLotFull;
    }
}
