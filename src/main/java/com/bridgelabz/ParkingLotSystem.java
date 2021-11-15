package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/***************************************************************************************
 * purpose --> ParkingLotSystem is class where we are going to create parkingLot system
 *  Here we going perform different parkingLot Operations.
 *
 * @author Sunil
 * @since 09/11/2021
 *
 ****************************************************************************************/
public class ParkingLotSystem {
    private final List vehicles;
    private final List<ParkingLotObserver> observers;
    private int actualCapacity;
    private Object vehicle;


    /**
     * constructor to take capacity as input
     */
    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    /**
     * purpose: method to add parkingLot Observer as owner or securityStaff
     *
     * @param observer owner or securityStaff as parameter
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * purpose: this method is to set Parking lor capacity
     *
     * @param capacity integer value
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * purpose : this method is to park the vehicles and checks whether the lot is full and
     * already parked with the another vehicle.
     *
     * @param vehicle
     * @throws ParkingLotException throws if there is already a vehicle parked/parking lot full
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("vehicle already parked");
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        this.vehicles.add(vehicle);
    }

    /**
     * purpose: method to check vehicle is parked or not
     *
     * @param vehicle
     * @return true/false
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicles.contains(vehicle);
    }

    /**
     * purpose: unPark is method for unParking the Vehicle, if there is already a parked vehicle then unPark will
     * be done. otherwise throws exception.
     *
     * @param vehicle
     * @throws ParkingLotException if no vehicle to park
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            return false;
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
            return true;
        }
        return false;
    }

    /**
     * purpose: method to check vehicle is unParked or not
     *
     * @param vehicle
     * @return vehicle is unParked
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return !this.vehicles.contains(vehicle);
    }

}
