package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingLotSystem.park(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.UnPark(vehicle);
        Assertions.assertTrue(isUnParked);

    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.park(new Object());
        Assertions.assertFalse(isParked);
    }
}
