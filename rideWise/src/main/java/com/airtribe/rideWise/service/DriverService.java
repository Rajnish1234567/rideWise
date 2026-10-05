package com.airtribe.rideWise.service;

import com.airtribe.rideWise.exception.InvalidInputException;
import com.airtribe.rideWise.exception.NoDriverFoundException;
import com.airtribe.rideWise.model.Driver;
import com.airtribe.rideWise.model.VehicleType;
import com.airtribe.rideWise.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverService {
    private final List<Driver> drivers;

    public DriverService() {
        this.drivers = new ArrayList<>();
    }

    public Driver registerDriver(String name, String location, VehicleType vehicleType) {

        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Driver name cannot be empty.");
        }

        if (location == null || location.isBlank()) {
            throw new InvalidInputException("Driver location cannot be empty.");
        }

        if (vehicleType == null) {
            throw new InvalidInputException("Vehicle type is required.");
        }

        Driver driver = new Driver(IdGenerator.generateDriverId(), name, location, vehicleType);
        drivers.add(driver);
        return driver;
    }

    public Optional<Driver> getDriverById(int id) {
        return drivers.stream()
                .filter(driver -> driver.getId() == id)
                .findFirst();
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.stream()
                .filter(Driver::isAvailable)
                .toList();
    }

    public void updateAvailability(int driverId, boolean available) {
        Driver driver = getDriverById(driverId)
                .orElseThrow(() ->
                        new NoDriverFoundException("Driver not found: " + driverId)
                );
        driver.setAvailable(available);
    }

    public List<Driver> getAllDrivers() {
        return List.copyOf(drivers);
    }
}
