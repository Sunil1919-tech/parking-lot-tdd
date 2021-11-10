package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.park(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Object vehicle = new Object();
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.UnPark(vehicle);
        Assertions.assertTrue(isUnParked);

    }
}
