package com.airtribe.rideWise.strategy;

import com.airtribe.rideWise.model.Ride;

public interface FareStrategy {
    double calculateFare(Ride ride);
}
