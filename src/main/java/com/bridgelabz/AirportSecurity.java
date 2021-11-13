package com.bridgelabz;

public class AirportSecurity implements ParkingLotObserver {
    private boolean isParkingLotFull;

    public void capacityIsFull() {
        isParkingLotFull = true;
    }

    public boolean isCapacityFull() {
        return this.isParkingLotFull;
    }

}
