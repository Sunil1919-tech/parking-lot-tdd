package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***************************************************************************************
 * purpose --> ParkingLotSystem is class where we are going to create parkingLot system
 *  Here we going perform different parkingLot Operations.
 *
 * @author Sunil
 * @since 09/11/2021
 ****************************************************************************************/
public class ParkingLotSystem {
    public static Map<Vehicle, LocalDateTime> dateTimeMap = new HashMap<>();
    private List<Vehicle> parkingLot1;
    private List<Vehicle> parkingLot2;
    private List<Vehicle> handicappedParkingLot;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    private Vehicle vehicle;

    /**
     * constructor declared with list of objects,ie observer and regular parking lot lists and list for handicap
     */
    public ParkingLotSystem() {
        this.observers = new ArrayList<>();
        this.parkingLot1 = new ArrayList<>();
        this.parkingLot2 = new ArrayList<>();
        this.handicappedParkingLot = new ArrayList<>();
    }

    /**
     * purpose: method to get the parking time of parked vehicle
     *
     * @param vehicle: the vehicle which has to know the parking time
     * @return : present parking time
     */
    public static LocalDateTime getParkingTime(Vehicle vehicle) {
        LocalDateTime now = LocalDateTime.now();
        dateTimeMap.put(vehicle, now);
        return now;
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
     * @return
     */
    public ParkingLotSystem setCapacity(int capacity) {
        this.actualCapacity = capacity;
        return null;
    }

    /**
     * purpose: this method is to park the parkingLot1 and checks whether the lot is full and
     * already parked with the another vehicle.
     *
     * @param vehicle it is parameter that has parking lot system vehicle object
     * @throws ParkingLotException throws if there is already a vehicle parked/parking lot full
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if ((parkingLot1.size() != actualCapacity) || (parkingLot2.size() != actualCapacity)) {
            this.vehicle = vehicle;
            if (parkingLot1.contains(vehicle) || parkingLot2.contains(vehicle))
                throw new ParkingLotException
                        (ParkingLotException.ExceptionType.ALREADY_VEHICLE_PARKED);
            if (parkingLot1.size() >= parkingLot2.size())
                parkingLot2.add(vehicle);
            else
                parkingLot1.add(vehicle);
        } else {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL);
        }
    }

    /**
     * purpose:method to check the vehicle is parked or not in the regular parking lots
     *
     * @param vehicle the vehicle in the parking lot
     * @return: boolean value true/false
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        return isParked;
    }

    /**
     * purpose: unPark is method for unParking the Vehicle, if there is already a parked vehicle then unPark will
     * be done. otherwise throws exception.
     *
     * @param vehicle this vehicle as  Vehicle object in the unPark method, checks whether upark vehicle is same as this vehicle
     * @throws ParkingLotException if no vehicle to park
     */
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_NOT_PARKED);
        if (parkingLot1.contains(vehicle)) {
            parkingLot1.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
        } else if (parkingLot2.contains(vehicle)) {
            parkingLot2.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
        } else {
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_NOT_PARKED);
        }
    }

    /**
     * purpose: method to check vehicle is unParked or not
     *
     * @param vehicle checks this vehicle and Vehicle of this method are same
     *                and checks vehicle object removed from the lot
     * @return vehicle is unParked/null
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !(parkingLot1.contains(vehicle) || parkingLot2.contains(vehicle));
    }

    /**
     * purpose: method to Find  required vehicle that has unPark
     *
     * @param vehicle the Vehicle object where driver wants to find the vehicle from the lot
     * @return vehicle want to find
     */
    public Vehicle searchVehicle(Vehicle vehicle) throws ParkingLotException {
        if (parkingLot2.contains(vehicle)) {
            return vehicle;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    /**
     * purpose- this getVehiclePosition method to check the position of  white color vehicle from
     * the two parking lots
     *
     * @param vehicle:     it as parameter of Vehicle Object which is parked in the parking lot
     * @param color:       parameter to check the white color car in the vehicles list
     * @param vehicleName: name of the vehicle that has to check for position
     * @throws ParkingLotException if none of the white cars are parked then throws exception
     */
    public int getVehiclePosition(Vehicle vehicle, String color, String vehicleName) throws ParkingLotException {
        if (parkingLot1.contains(vehicle)) {
            vehicle.getColor().equals(color);
            vehicle.getVehicleName().equals(vehicleName);
            for (Object position : parkingLot1) {
                if (position.equals(vehicle))
                    return parkingLot1.indexOf(position);
            }
        } else if (parkingLot2.contains(vehicle)) {
            vehicle.getColor().equals(color);
            vehicle.getVehicleName().equals(vehicleName);
            for (Object position : parkingLot2) {
                if (position.equals(vehicle))
                    return parkingLot2.indexOf(position);
            }
        } else
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
        return 0;
    }

    /**
     * purpose - to get a valid numberPlate with required pattern. Here using regex pattern
     *
     * @param vehicleNumber the parked vehicle number from the vehicle lists
     * @return the number matched with the pattern requirements
     */
    public boolean validateVehicleNumber(String vehicleNumber) {
        if (vehicleNumber.isEmpty())
            return false;
        Pattern pattern = Pattern.compile("[A-Z]{2}[ -][0-9]{1,2}[A-Z]{1,2}[0-9]{3,4}");
        Matcher matcher = pattern.matcher(vehicleNumber);
        return matcher.matches();
    }

    /**
     * purpose: method to park handicap driver vehicle when there is space to park in the separate lot
     * ie handicapParkingLot
     *
     * @param driverType: this parameter checks whether the driver is handicap or normal
     * @param vehicle:    the vehicle object parameter has parking vehicle value
     * @throws ParkingLotException: if the parking lot is full throws the exception
     */
    public void handicapPark(DriverType driverType, Vehicle vehicle) throws ParkingLotException {
        if (driverType == DriverType.HANDICAPPED) {
            if (handicappedParkingLot.size() != actualCapacity)
                handicappedParkingLot.add(vehicle);
        } else
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL);
    }

    /**
     * purpose: to check the handicapped driver has parked the vehicle or not
     *
     * @param vehicle:parameter which has to check that vehicle is parked in the handicappedLot
     * @return: parked vehicle
     */
    public boolean isHandicapVehicleParked(Vehicle vehicle) {
        return handicappedParkingLot.contains(vehicle);
    }
}