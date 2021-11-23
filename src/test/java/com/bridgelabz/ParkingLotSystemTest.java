package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        vehicle =new Vehicle("Polo","TS-007", "grey");
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
        Vehicle anotherVehicle = new Vehicle("Toyota", "TS-9999", "meroon");
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
        Vehicle vehicle2 = new Vehicle("Supra", "TS-8888", "silver");
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
            parkingLotSystem.park(new Vehicle("Opel", "TS-7777", "chaco"));
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
    void givenCapacityIs2_ShouldAbleToPark2vehicleList1() throws ParkingLotException {
        Vehicle vehicle2 = new Vehicle("MG", "TS-6666", "red");
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
            parkingLotSystem.park(new Vehicle("Kia", "TS-5555", "white"));
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
        vehicle=new Vehicle("Lancer","TS-1331", "white");
        Vehicle vehicle1 =new Vehicle("Skoda","TS-1441", "yellow");
        Vehicle vehicle2 = new Vehicle("Bentley", "TS-4444", "blue");
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
        vehicle = new Vehicle("lancer","TS-1221", "black");
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
    void givenAVehicle_WhenParked_ShouldReturnParkingDateAndTime() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("Volvo","TS-8055","red");
        parkingLotSystem.park(vehicle);
        String parkTime = parkingLotSystem.getParkingTime();
        Assertions.assertEquals(parkTime,"13:33");
    }

    @Test
    void givenAVehicle_whenParked_ShouldEvenlyDistributeTwoParkingSlots() throws ParkingLotException {
        Vehicle vehicle1 =new Vehicle("HM","TS-999","red");
        Vehicle vehicle2 =new Vehicle("HM","TS-777","blue");
        Vehicle vehicle3 =new Vehicle("HM","TS-666","white");
        Vehicle vehicle4 =new Vehicle("HM","TS-555","black");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        boolean isParked =parkingLotSystem.isVehicleParked(vehicle4);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenVehicles_whenWhiteColorCarParked_ShouldReturnThePosition() throws ParkingLotException {
        vehicle = new Vehicle("Lexus", "TS-757", "Blue");
        Vehicle vehicle1 = new Vehicle("polo", "TS-989", "white");
        Vehicle vehicle2 = new Vehicle("renault", "TS-982", "green");
        Vehicle vehicle3 = new Vehicle("Honda civic", "TS-981", "yellow");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        int vehiclePosition = parkingLotSystem.getVehiclePosition(vehicle1, "white", "Lexux");
        Assertions.assertEquals(2, vehiclePosition);
    }

    @Test
    void givenVehicles_whenToyotoBlueColorParked_ShouldReturnThePosition() throws ParkingLotException {
        vehicle = new Vehicle("Lexus", "TS-757", "Blue");
        Vehicle vehicle1 = new Vehicle("polo", "TS-989", "white");
        Vehicle vehicle2 = new Vehicle("renault", "TS-982", "green");
        Vehicle vehicle3 = new Vehicle("Honda civic", "TS-981", "yellow");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        int vehiclePosition = parkingLotSystem.getVehiclePosition(vehicle, "Blue", "Lexus");
        Assertions.assertEquals(0, vehiclePosition);
    }

    @Test
    void givenVehicle_whenVehicleNumberValidated_ShouldReturnTrue() {
        vehicle = new Vehicle("Contessa", "TS-42B805", "Black");
        boolean isValidNumber = parkingLotSystem.validateVehicleNumber(vehicle.getVehicleNum());
        Assertions.assertTrue(isValidNumber);
    }
}