package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    void givenParkingLot_WhenVehicleParked_ShouldReturnTrue() {
        ParkingLotService parkingLotService = new ParkingLotService();
        boolean vehicleParked = parkingLotService.park(new Object());
        Assertions.assertTrue(vehicleParked);
    }
}
