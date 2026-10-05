package com.airtribe.rideWise.service;

import com.airtribe.rideWise.exception.NoDriverAvailableException;
import com.airtribe.rideWise.model.*;
import com.airtribe.rideWise.strategy.FareStrategy;
import com.airtribe.rideWise.strategy.RideMatchingStrategy;
import com.airtribe.rideWise.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RideService {
    private final RideMatchingStrategy rideMatchingStrategy;
    private final FareStrategy fareStrategy;

    private final DriverService driverService;
    private final List<Ride> rides;

    public RideService(RideMatchingStrategy rideMatchingStrategy, FareStrategy fareStrategy, DriverService driverService) {
        this.rideMatchingStrategy = rideMatchingStrategy;
        this.fareStrategy = fareStrategy;
        this.driverService = driverService;
        this.rides = new ArrayList<>();
    }

    public Ride requestRide(Rider rider, double distance) {
        if (rider == null) {
            throw new IllegalArgumentException("Rider cannot be null.");
        }

        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }

        List<Driver> availableDrivers = driverService.getAvailableDrivers();

        if (availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException("No drivers are currently available.");
        }

        Ride ride = new Ride(IdGenerator.generateRideId(), rider, distance);

        Driver selectedDriver = rideMatchingStrategy.findDriver(rider, availableDrivers);

        if (selectedDriver == null) {
            throw new NoDriverAvailableException("No suitable driver found.");
        }

        ride.assignDriver(selectedDriver);
        selectedDriver.setAvailable(false);
        selectedDriver.incrementActiveRideCount();
        rides.add(ride);

        return ride;
    }

    public FareReceipt completeRide(int rideId) {
        Ride ride = getRideById(rideId)
                .orElseThrow(() -> new IllegalArgumentException("Ride not found: " + rideId));

        if (ride.getStatus() != RideStatus.ASSIGNED) {
            throw new IllegalStateException("Only assigned rides can be completed.");
        }

        double fare = fareStrategy.calculateFare(ride);

        FareReceipt receipt = new FareReceipt(ride.getId(), fare);

        ride.completeRide(receipt);

        Driver driver = ride.getDriver();
        driver.decrementActiveRideCount();
        driver.setAvailable(true);
        return receipt;
    }

    public void cancelRide(int rideId) {

        Ride ride = getRideById(rideId)
                .orElseThrow(() ->new IllegalArgumentException("Ride not found: " + rideId));

        if (ride.getStatus() != RideStatus.ASSIGNED
                && ride.getStatus() != RideStatus.REQUESTED) {
            throw new IllegalStateException("Ride cannot be cancelled in status: " + ride.getStatus());
        }

        if (ride.getDriver() != null) {
            Driver driver = ride.getDriver();
            driver.decrementActiveRideCount();
            driver.setAvailable(true);
        }
        ride.cancelRide();
    }

    public Optional<Ride> getRideById(int rideId) {
        return rides.stream()
                .filter(ride -> ride.getId() == rideId)
                .findFirst();
    }

    public List<Ride> getAllRides() {
        return List.copyOf(rides);
    }
}
