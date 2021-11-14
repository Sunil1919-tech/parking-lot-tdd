package com.bridgelabz;

/**
 * purpose: to separate the methods from parking lot system that call on
 *          parkingLotOwner and AirportSecurity
 *
 * @author Sunil
 * @since 11/11/2021
 */
public interface ParkingLotObserver {
    public void capacityIsFull();

    public void capacityIsAvailable();
}
