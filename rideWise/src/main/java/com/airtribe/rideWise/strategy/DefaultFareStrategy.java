package com.airtribe.rideWise.strategy;

import com.airtribe.rideWise.model.Driver;
import com.airtribe.rideWise.model.Ride;
import com.airtribe.rideWise.model.VehicleType;

public class DefaultFareStrategy implements FareStrategy{

    @Override
    public double calculateFare(Ride ride) {

        Driver driver = ride.getDriver();

        if (driver == null) {
            throw new IllegalStateException("Cannot calculate fare before driver assignment.");
        }

        double baseFare;
        double perKmRate;

        VehicleType vehicleType = driver.getVehicleType();

        perKmRate = switch (vehicleType) {
            case BIKE -> {
                baseFare = 30;
                yield 8;
            }
            case AUTO -> {
                baseFare = 40;
                yield 12;
            }
            case CAR -> {
                baseFare = 80;
                yield 18;
            }
            default -> throw new IllegalArgumentException("Unsupported vehicle type.");
        };
        return baseFare + (ride.getDistance() * perKmRate);
    }
}