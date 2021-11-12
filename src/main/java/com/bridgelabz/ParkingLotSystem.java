package com.bridgelabz;

/***************************************************************************************
 *
 * purpose --> ParkingLotSystem is class where we are going to create parkingLot system
 * having couple of lots with the capacity 100 each. Here we going perform different parkingLot Operations.
 *
 * @author Sunil
 * @since 09/11/2021
 *
 ****************************************************************************************/
public class ParkingLotSystem {
    private Object vehicle;
    private ParkingLotOwner owner;
    private int currentCapacity;
    private int actualCapacity;


    /**
     * constructor to take capacity as input
     *
     * @param capacity parking lot capacity
     */
    public ParkingLotSystem(int capacity) {
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /***********************************************************************************************************
     * purpose --> park is method for parking the vehicle ,if the parking lot is null it will going to park the
     *              vehicle and if there is already a vehicle it will throw exception
     *
     * @param vehicle
     * @throws ParkingLotException
     ***********************************************************************************************************/
    public void park(Object vehicle) throws ParkingLotException {
        if (this.currentCapacity == this.actualCapacity) {
            owner.capacityIsFull();
            throw new ParkingLotException("Parking Lot is Full");
        }
        this.currentCapacity++;
        this.vehicle = vehicle;
    }

    /*********************************************************************************************************
     * boolean type to check whether vehicle is parked or not.
     *
     * @param vehicle
     * @return returns True if there is a vehicle
     * false if it is null
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }

    /***************************************************************************************************************
     * purpose --> unPark is method for unParking the Vehicle, if there is already a parked vehicle then unPark will
     *              be done. otherwise throws exception.
     *
     * @param vehicle
     * @throws ParkingLotException
     **************************************************************************************************************/
    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("there is no vehicle to UnPark");
        } else if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
    }

    /************************************************************************************************************
     * boolean type to check vehicle is unParked or not.
     *
     * @param vehicle
     * @return true if unParking Done
     * otherwise false
     **************************************************************************************************************/
    public boolean isVehicleUnParked(Object vehicle) {
        return this.vehicle == null;
    }

    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }
}
