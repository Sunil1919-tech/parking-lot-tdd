package com.bridgelabz;

/***************************************************************************************
 *
 * purpose --> ParkingLotSystem is class where we are going to create parkingLot system
 * having couple of lots with the capacity 100 each.
 *
 * @author Sunil
 * @since 09/11/2021
 *
 ****************************************************************************************/
public class ParkingLotSystem {
    private Object vehicle;

    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new  ParkingLotException("Parking Lot is Full");
        this.vehicle = vehicle;
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null)
            return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
