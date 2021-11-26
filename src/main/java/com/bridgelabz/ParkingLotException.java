package com.bridgelabz;

/**
 * purpose: To perform the parkingLot System Exception
 *
 * @author Sunil
 * @since 11/11/2021
 */
public class ParkingLotException extends RuntimeException {
    public ExceptionType exceptionType;

    //constructor initialized with exceptionType
    public ParkingLotException( ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * this enum class has the Exception types which are throwable
     */
    public enum ExceptionType {
        PARKING_LOT_IS_FULL, VEHICLE_NOT_FOUND, ALREADY_VEHICLE_PARKED,
        VEHICLE_NOT_PARKED;
    }
}
