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
    private static List vehicles;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;


    /**
     * @param capacity
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
     * @param capacity integer value that capacity given to the lot
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * purpose : this method is to park the vehicles and checks whether the lot is full and
     * already parked with the another vehicle.
     *
     * @param vehicle it is parameter that has parking lot system vehicle object
     * @throws ParkingLotException throws if there is already a vehicle parked/parking lot full
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Already a vehicle Parked",
                    ParkingLotException.ExceptionType.ALREADY_VEHICLE_PARKED);
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking lot is full", ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL);
        }
        this.vehicles.add(vehicle);
    }

    /**
     * purpose: method to check vehicle is parked or not
     *
     * @param vehicle this checks the parked vehicle is same a this vehicle
     * @return true/false
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicles.contains(vehicle);
    }

    /**
     * purpose: unPark is method for unParking the Vehicle, if there is already a parked vehicle then unPark will
     * be done. otherwise throws exception.
     *
     * @param vehicle this vehicle as an object in the unPark method, checks whether upark vehicle is same as this vehicle
     * @throws ParkingLotException if no vehicle to park
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("No vehicle to UnPark", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
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
     * @param vehicle checks this vehicle and Object of this method are same
     *                and checks vehicle object removed from the lot
     * @return vehicle is unParked/null
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return !this.vehicles.contains(vehicle);
    }

    /**
     * method to search required vehicle
     *
     * @param vehicle the object where driver wants to find the vehicle from the lot
     * @return vehicle want to find
     */
    public Object searchVehicle(Object vehicle) throws ParkingLotException {
        if (vehicles.contains(vehicle)) {
            return vehicle;
        }
        throw new ParkingLotException("there is no such vehicle", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }
}
