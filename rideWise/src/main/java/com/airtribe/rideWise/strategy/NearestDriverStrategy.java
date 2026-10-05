package com.airtribe.rideWise.strategy;

import com.airtribe.rideWise.model.Driver;
import com.airtribe.rideWise.model.Rider;

import java.util.List;

public class NearestDriverStrategy implements RideMatchingStrategy{
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {

        Driver fallbackDriver = null;

        for (Driver driver : drivers) {

            if (!driver.isAvailable()) {
                continue;
            }

            if (driver.getCurrentLocation().equalsIgnoreCase(rider.getLocation())) {
                return driver;
            }

            if (fallbackDriver == null) {
                fallbackDriver = driver;
            }
        }
        return fallbackDriver;
    }
}