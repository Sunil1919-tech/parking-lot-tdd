package com.bridgelabz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static List<Vehicle> vehicleList1;
    private static List<Vehicle> vehicleList2;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    private String parkingTime;
    private Vehicle vehicle;

    /**
     * constructor declared with list of objects,ie observer and vehicleList1 list
     *
     * @param capacity intitializing the capacity of parking lot
     */
    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicleList1 = new ArrayList<>();
        this.vehicleList2 = new ArrayList<>();
        this.actualCapacity = capacity;
        this.parkingTime = getParkingTime();
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
     * purpose : this method is to park the vehicleList1 and checks whether the lot is full and
     * already parked with the another vehicle.
     *
     * @param vehicle it is parameter that has parking lot system vehicle object
     * @throws ParkingLotException throws if there is already a vehicle parked/parking lot full
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if ((vehicleList1.size() != actualCapacity) || (vehicleList2.size() != actualCapacity)) {
            this.vehicle = vehicle;
            if (isVehicleParked(vehicle))
                throw new ParkingLotException("Already a vehicle Parked",
                        ParkingLotException.ExceptionType.ALREADY_VEHICLE_PARKED);
            if (vehicleList1.size() >= vehicleList2.size())
                vehicleList1.add(vehicle);
            else
                vehicleList1.add(vehicle);
        } else if (this.vehicleList1.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking lot is full", ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL);
        }
        this.vehicleList1.add(vehicle);
    }

    /**
     * purpose: method to check vehicle is parked or not
     *
     * @param vehicle this checks the parked vehicle is same a this vehicle
     * @return true/false
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return ParkingLotSystem.vehicleList1.contains(vehicle);
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
            throw new ParkingLotException("No vehicle to UnPark", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
        if (this.vehicleList1.contains(vehicle)) {
            this.vehicleList1.remove(vehicle);
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
        return !this.vehicleList1.contains(vehicle);
    }

    /**
     * method to Find  required vehicle
     *
     * @param vehicle the Vehicle object where driver wants to find the vehicle from the lot
     * @return vehicle want to find
     */
    public Vehicle searchVehicle(Vehicle vehicle) throws ParkingLotException {
        if (vehicleList1.contains(vehicle)) {
            return vehicle;
        }
        throw new ParkingLotException("there is no such vehicle", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    /**
     * method to get the parking time for vehicle in parking lot
     * @return parkingTime ,the time of the slot in the parking lot
     */
    public String getParkingTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        parkingTime = dateTime.format(formatter);
        return parkingTime;
    }

    /**
     * this getVehiclePosition method to check the position of  white color vehicle from
     * the two parking lots
     *
     * @param vehicle it as parameter of Vehicle Object which is parked in the parking lot
     * @param color parameter to check the white color car in the vehicles list
     * @param vehicleName
     * @throws ParkingLotException if none of the white cars are parked then throws exception
     */
    public int getVehiclePosition(Vehicle vehicle, String color, String vehicleName) throws ParkingLotException {
        if (vehicleList1.contains(vehicle)) {
            vehicle.getColor().equals(color);
            vehicle.getVehicleName().equals(vehicleName);
            for (Object position : vehicleList1) {
                if (position.equals(vehicle))
                    return vehicleList1.indexOf(position);
            }
        } else if (vehicleList2.contains(vehicle)) {
            vehicle.getColor().equals(color);
            vehicle.getVehicleName().equals(vehicleName);
            for (Object position : vehicleList2) {
                if (position.equals(vehicle))
                    return vehicleList2.indexOf(position);
            }
        } else
            throw new ParkingLotException("No vehicle found", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
        return 0;
    }
}
