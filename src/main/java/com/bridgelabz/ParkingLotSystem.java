package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************************************************************************************
 * purpose --> ParkingLotSystem is class where we are going to create parkingLot system
 *  Here we going perform different parkingLot Operations.
 *
 * @author Sunil
 * @since 09/11/2021
 *
 ****************************************************************************************/
public class ParkingLotSystem {
    private static List <Vehicle>  vehicles;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    public static Map<Vehicle, LocalDateTime> localDateTime = new HashMap<>();

    /**
     * constructor declared with list of objects,ie observer and vehicles list
     *
     * @param capacity intitializing the capacity of parking lot
     */
    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
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
    public void park(Vehicle vehicle) throws ParkingLotException {
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
    public boolean isVehicleParked(Vehicle vehicle) {
        return this.vehicles.contains(vehicle);
    }

    /**
     * purpose: unPark is method for unParking the Vehicle, if there is already a parked vehicle then unPark will
     * be done. otherwise throws exception.
     *
     * @param vehicle this vehicle as  Vehicle object in the unPark method, checks whether upark vehicle is same as this vehicle
     * @throws ParkingLotException if no vehicle to park
     */
    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw  new ParkingLotException("No vehicle to UnPark", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
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
     * @param vehicle checks this vehicle and Vehicle of this method are same
     *                and checks vehicle object removed from the lot
     * @return vehicle is unParked/null
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !this.vehicles.contains(vehicle);
    }

    /**
     * method to Find  required vehicle
     *
     * @param vehicle the Vehicle object where driver wants to find the vehicle from the lot
     * @return vehicle want to find
     */
    public Vehicle searchVehicle(Vehicle vehicle) throws ParkingLotException {
        if (vehicles.contains(vehicle)) {
            return vehicle;
        }
        throw new ParkingLotException("there is no such vehicle", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    /**
     * method to check the parking time and date
     *
     * @param vehicle Vehicle object  that parked in lot with particular date and time
     * @return time and date of parked vehicle
     */
    public static LocalDateTime parkingTime(Vehicle vehicle) {
        LocalDateTime present = LocalDateTime.now();
        localDateTime.put(vehicle, present);
        return present;
    }
}
