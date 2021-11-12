package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Object vehicle;
    ParkingLotOwner owner;

    @BeforeEach
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle = new Object();
        owner = new ParkingLotOwner();
        parkingLotSystem.registerOwner(owner);
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
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(null);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldThrowException() throws ParkingLotException {
        try {
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Vehicle already Parked", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUParkedAnotherVehicle_ShouldReturnFalse() {
        Object anotherVehicle = new Object();
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(anotherVehicle);
            Assertions.assertFalse(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenNotUnParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(null);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() throws ParkingLotException {
        Object anotherVehicle = new Object();
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(anotherVehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenParkingLot_WhenFull_ShouldInformToOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerOwner(owner);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {}
            boolean isFull = owner.isParkingLotFull();
            Assertions.assertTrue(isFull);
    }

    @Test
    void givenParkingLot_WhenNotFull_ShouldInformOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerOwner(owner);
        boolean isNotFull = owner.isParkingLotFull();
        Assertions.assertFalse(isNotFull);
    }
}
