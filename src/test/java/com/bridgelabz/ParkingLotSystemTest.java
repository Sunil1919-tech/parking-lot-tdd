package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle = new Object();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }


    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);

    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldThrowException() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle), "vehicle already parked");
    }

    @Test
    void givenAVehicle_WhenParkedAnotherVehicle_ShouldThrowException() {
        Object anotherVehicle = new Object();
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(anotherVehicle);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.exceptionType);
        }
    }

    @Test
    void givenAVehicle_WhenNotUnParked_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() throws ParkingLotException {
        Object vehicle2 = new Object();
        parkingLotSystem.park(vehicle);
        try {
            parkingLotSystem.unPark(vehicle2);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.class, e.exceptionType);
        }
    }

    @Test
    void givenAVehicleNull_whenUnParked_ShouldReturnException() {

        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(null));
    }

    @Test
    void givenParkingLot_WhenFull_ShouldInformToOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
            boolean isFull = owner.isCapacityFull();
            Assertions.assertTrue(isFull);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.exceptionType);
        }
    }


    @Test
    void givenParkingLot_WhenNotFull_ShouldInformOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        boolean isFull = owner.isCapacityFull();
        Assertions.assertFalse(isFull);
    }

    @Test
    void givenCapacityIs2_ShouldAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);

    }


    @Test
    void givenParkingLot_WhenFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
            boolean isFull = airportSecurity.isCapacityFull();
            Assertions.assertTrue(isFull);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.PARKINGLOT_IS_FULL, e.exceptionType);
        }
    }


    @Test
    void givenWhenParkingLotIsNotFull_ShouldSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        boolean isFull = airportSecurity.isCapacityFull();
        Assertions.assertFalse(isFull);
    }

    @Test
    void whenParkingLotHasSpaceAgainAfterFull_ShouldInformTheOwner() throws ParkingLotException {
        Object vehicle2 = new Object();
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
        });
        parkingLotSystem.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }


    @Test
    void givenAParkingLotAttendant_whenParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.attendantParked(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAParkingLotAttendant_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotSystem.park(vehicle);
        parkingLotAttendant.attendantUnParked(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_WhenDriverWantsToFind_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        Object vehicleToFind = parkingLotSystem.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, vehicleToFind);
    }
}