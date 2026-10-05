package com.airtribe.rideWise.strategy;

import com.airtribe.rideWise.model.Driver;
import com.airtribe.rideWise.model.Rider;

import java.util.Comparator;
import java.util.List;

public class LeastActiveDriverStrategy implements RideMatchingStrategy{

    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {

        return drivers.stream()
                .filter(Driver::isAvailable)
                .min(Comparator.comparingInt(Driver::getActiveRideCount))
                .orElse(null);
    }
}