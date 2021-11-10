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
        if (this.vehicle == null)
            this.vehicle =vehicle;
        else if(this.vehicle.equals(vehicle))
            throw new  ParkingLotException("Parking Lot is Full");

    }

    public boolean isVehicleParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }

    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("there is no vehicle to Unpark");
        } else if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;

        }

    }

    public boolean isVehicleUnParked(Object vehicle) {
        return this.vehicle == null;
    }
    public boolean isParkingLotFull() {
        return this.vehicle!=null;
    }
}
