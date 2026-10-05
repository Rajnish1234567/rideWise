package com.airtribe.rideWise.strategy;

import com.airtribe.rideWise.model.Ride;

public class PeakHourFareStrategy implements FareStrategy{

    private final FareStrategy baseFareStrategy;
    private final double surgeMultiplier;

    public PeakHourFareStrategy(FareStrategy baseFareStrategy, double surgeMultiplier) {
        this.baseFareStrategy = baseFareStrategy;
        this.surgeMultiplier = surgeMultiplier;
    }

    @Override
    public double calculateFare(Ride ride) {

        double normalFare = baseFareStrategy.calculateFare(ride);

        return normalFare * surgeMultiplier;
    }
}