package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;

    @BeforeEach
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
//        vehicle = new Vehicle();
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
        vehicle =new Vehicle("Polo","TS-007");
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
        Vehicle anotherVehicle = new Vehicle("Toyota", "TS-9999");
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
        Vehicle vehicle2 = new Vehicle("Supra", "TS-8888");
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
            parkingLotSystem.park(new Vehicle("Opel", "TS-7777"));
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
        Vehicle vehicle2 = new Vehicle("MG", "TS-6666");
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
            parkingLotSystem.park(new Vehicle("Kia", "TS-5555"));
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
        parkingLotSystem.setCapacity(3);
        vehicle=new Vehicle("Lancer","TS-1331");
        Vehicle vehicle1 =new Vehicle("Skoda","TS-1441");
        Vehicle vehicle2 = new Vehicle("Bentley", "TS-4444");
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle1);
       parkingLotSystem.unPark(vehicle1);
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
        vehicle = new Vehicle("lancer","TS-1221");
        parkingLotSystem.park(vehicle);
        parkingLotAttendant.attendantUnParked(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_WhenDriverWantsToFind_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        Vehicle vehicleToFind = parkingLotSystem.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, vehicleToFind);
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnDateAndTime() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("Contessa", "TS-1234");
        parkingLotSystem.park(vehicle);
        Assertions.assertEquals(LocalDateTime.now(), ParkingLotSystem.parkingTime(vehicle));
    }
}