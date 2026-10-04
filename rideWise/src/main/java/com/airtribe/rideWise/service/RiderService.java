package com.airtribe.rideWise.service;

import com.airtribe.rideWise.exception.InvalidInputException;
import com.airtribe.rideWise.model.Rider;
import com.airtribe.rideWise.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RiderService {

    private final List<Rider> riders;

    public RiderService() {
        this.riders = new ArrayList<>();
    }

    public Rider registerRider(String name, String location) {

        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Rider name cannot be empty.");
        }

        if (location == null || location.isBlank()) {
            throw new InvalidInputException("Rider location cannot be empty.");
        }

        Rider rider = new Rider(IdGenerator.generateRiderId(), name, location);
        riders.add(rider);
        return rider;
    }

    public Optional<Rider> getRiderById(int id) {
        return riders.stream()
                .filter(rider -> rider.getId() == id)
                .findFirst();
    }

    public List<Rider> getAllRiders() {
        return List.copyOf(riders);
    }
}
