package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/***************************************************************************************
 *
 * purpose --> ParkingLotSystem is class where we are going to create parkingLot system
 * having couple of lots with the capacity 100 each. Here we going perform different parkingLot Operations.
 *
 * @author Sunil
 * @since 09/11/2021
 *
 ****************************************************************************************/
public class ParkingLotSystem {
    private List vehicles;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    private Object vehicle;


    /**
     * constructor to take capacity as input
     *
     * @param capacity parking lot capacity
     */
    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("vehicle already parked");
        this.vehicles.add(vehicle);
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    /**
     * purpose --> unPark is method for unParking the Vehicle, if there is already a parked vehicle then unPark will
     * be done. otherwise throws exception.
     *
     * @param vehicle
     * @throws ParkingLotException
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            return false;
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            return true;
        }
        return false;
    }

    public boolean isVehicleUnParked(Object vehicle) {
        return !this.vehicles.contains(vehicle);
    }

//    public boolean isParkingLotFull() {
//        return this.vehicles.size() == this.actualCapacity;
//    }

}
