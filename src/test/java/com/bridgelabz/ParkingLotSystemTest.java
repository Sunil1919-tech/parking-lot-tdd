package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Vehicle vehicle;


    @BeforeEach
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.setCapacity(1);
        vehicle = new Vehicle("contessa", "TS-4568", "Violet", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldThrowException() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        vehicle = new Vehicle("Jaguar", "TS-1221", "Black", Vehicle.VehicleType.LARGE);
        parkingLotSystem.park(vehicle);
        Vehicle vehicle1 = new Vehicle("polo", "TS-1331", "White", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle1);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle1), "vehicle already parked");
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        vehicle = new Vehicle("Polo", "TS-007", "grey", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicleNull_WhenUnParked_ShouldThrowException() {
        Assertions.assertThrows
                (ParkingLotException.class, () -> parkingLotSystem.unPark(null));
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnException() throws ParkingLotException {
        Vehicle anotherVehicle = new Vehicle("Supra", "TS-8888", "silver", Vehicle.VehicleType.MEDIUM);
        vehicle = new Vehicle("MG", "TS-963", "meroon", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(anotherVehicle));
    }

    @Test
    void givenParkingLot_WhenFull_ShouldInformToOwner() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("zen", "TS-404", "White", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle2 = new Vehicle("Ambassador", "TS-2121", "Blue", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle3 = new Vehicle("Contessa", "TS-0007", "Black", Vehicle.VehicleType.MEDIUM);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.unPark(vehicle);
        }, "Parking Lot is Full");
    }

    @Test
    void givenParkingLot_WhenNotFull_ShouldInformOwner() {
        parkingLotSystem.setCapacity(2);
        vehicle = new Vehicle("Polo", "TS-007", "grey", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        boolean isFull = owner.isCapacityFull();
        Assertions.assertFalse(isFull);
    }

    @Test
    void givenCapacityIs2_ShouldAbleToPark2parkingLot() throws ParkingLotException {
        vehicle = new Vehicle("Polo", "TS-007", "grey", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle2 = new Vehicle("MG", "TS-6666", "red", Vehicle.VehicleType.MEDIUM);
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
    void givenParkingLot_WhenFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Vehicle("Kia", "TS-5555", "white", Vehicle.VehicleType.MEDIUM));
            boolean isFull = airportSecurity.isCapacityFull();
            Assertions.assertTrue(isFull);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, e.exceptionType);
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
        vehicle = new Vehicle("Lancer", "TS-1331", "white", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle1 = new Vehicle("Skoda", "TS-1441", "yellow", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle2 = new Vehicle("Bentley", "TS-4444", "blue", Vehicle.VehicleType.MEDIUM);
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.unPark(vehicle1);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

//    @Test
//    void givenAVehicle_whenParkedByAttendent_ShouldReturnTrue() {
//        parkingLotSystem.setCapacity(2);
//        vehicle = new Vehicle("Safari","TS-6979","Blue", Vehicle.VehicleType.LARGE);
//        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
//        parkingLotSystem.park(vehicle);
//        parkingLotAttendant.attendantParked(vehicle);
//        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
//        Assertions.assertTrue(isParked);
//    }

    @Test
    void givenAVehicle_WhenDriverWantsToFind_ShouldReturnTrue() {
        parkingLotSystem.setCapacity(1);
        vehicle = new Vehicle("Santro", "TS-008", "White", Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        Vehicle vehicleToFind = parkingLotSystem.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, vehicleToFind);
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnParkingDateAndTime() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        Vehicle vehicle = new Vehicle("Volvo", "TS-8055", "red", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle);
        Assertions.assertEquals(parkingLotSystem.getParkingTime(vehicle), LocalDateTime.now());
    }

    @Test
    void givenAVehicle_whenParked_ShouldEvenlyDistributeTwoParkingSlots() {
        parkingLotSystem.setCapacity(4);
        Vehicle vehicle1 = new Vehicle("HM", "TS-999", "red", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle2 = new Vehicle("HM", "TS-777", "blue", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle3 = new Vehicle("HM", "TS-666", "white", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle4 = new Vehicle("HM", "TS-555", "black", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle4);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenVehicles_whenWhiteColorCarParked_ShouldReturnThePosition() throws ParkingLotException {
        parkingLotSystem.setCapacity(4);
        vehicle = new Vehicle("Lexus", "TS-757", "Blue", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle1 = new Vehicle("polo", "TS-989", "white", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle2 = new Vehicle("renault", "TS-982", "green", Vehicle.VehicleType.MEDIUM);
        Vehicle vehicle3 = new Vehicle("Honda civic", "TS-981", "yellow", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        int vehiclePosition = parkingLotSystem.getVehiclePosition(vehicle1, "white", "Lexux");
        Assertions.assertEquals(0, vehiclePosition);
    }

    @Test
    void givenVehicles_whenToyotoBlueColorParked_ShouldReturnThePosition() throws ParkingLotException {
        parkingLotSystem.setCapacity(6);
        vehicle = new Vehicle("Toyoto", "TS-757", "Blue", Vehicle.VehicleType.SMALL);
        Vehicle vehicle1 = new Vehicle("polo", "TS-989", "white", Vehicle.VehicleType.SMALL);
        Vehicle vehicle2 = new Vehicle("renault", "TS-982", "green", Vehicle.VehicleType.SMALL);
        Vehicle vehicle3 = new Vehicle("Honda civic", "TS-981", "yellow", Vehicle.VehicleType.SMALL);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        int vehiclePosition = parkingLotSystem.getVehiclePosition(vehicle, "Blue", "Toyoto");
        Assertions.assertEquals(0, vehiclePosition);
    }

    @Test
    void givenVehicle_whenVehicleNumberValidated_ShouldReturnTrue() {
        parkingLotSystem.setCapacity(1);
        vehicle = new Vehicle("Contessa", "TS-42B805", "Black", Vehicle.VehicleType.MEDIUM);
        boolean isValidNumber = parkingLotSystem.validateVehicleNumber(vehicle.getVehicleNum());
        Assertions.assertTrue(isValidNumber);
    }

    @Test
    void givenAVehicle_WhenHandicappedParked_ShouldReturnLocation() {
        parkingLotSystem.setCapacity(1);
        vehicle = new Vehicle("MG", "TN-4141", "YELLOW", Vehicle.VehicleType.MEDIUM);
        parkingLotSystem.handicapPark(DriverType.HANDICAPPED, vehicle);
        boolean isParked = parkingLotSystem.isHandicapVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }
}