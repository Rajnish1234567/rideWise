package com.airtribe.rideWise.model;

public class Driver {
    private final int id;
    private String name;
    private String currentLocation;
    private boolean available;
    private VehicleType vehicleType;
    private int activeRideCount;

    public Driver(
            int id,
            String name,
            String currentLocation,
            VehicleType vehicleType
    ) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.vehicleType = vehicleType;
        this.available = true;
        this.activeRideCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getActiveRideCount() {
        return activeRideCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void incrementActiveRideCount() {
        activeRideCount++;
    }

    public void decrementActiveRideCount() {
        if (activeRideCount > 0) {
            activeRideCount--;
        }
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", vehicleType=" + vehicleType +
                ", available=" + available +
                ", activeRideCount=" + activeRideCount +
                '}';
    }
}
