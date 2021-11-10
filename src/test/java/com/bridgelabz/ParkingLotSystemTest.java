package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
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
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.UnPark(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

}
