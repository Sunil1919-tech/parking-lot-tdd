package com.bridgelabz;

/**
 * purpose: To perform the parkingLot System Exception
 *
 * @author Sunil
 * @since 11/11/2021
 */
public class ParkingLotException extends Exception {
    public ExceptionType exceptionType;

    //constructor initialized with variables having a message and exceptionType
    public ParkingLotException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    /**
     * this enum class has the Exception types which are throwable
     */
    public enum ExceptionType {
        PARKINGLOT_IS_FULL, VEHICLE_NOT_FOUND, ALREADY_VEHICLE_PARKED
    }
}
