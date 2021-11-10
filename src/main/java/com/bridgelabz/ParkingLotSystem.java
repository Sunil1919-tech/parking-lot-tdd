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

    public boolean park(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    public boolean UnPark(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
