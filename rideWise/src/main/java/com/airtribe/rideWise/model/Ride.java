package com.airtribe.rideWise.model;

public class Ride {
    private final int id;
    private final Rider rider;

    private Driver driver;
    private double distance;
    private RideStatus status;
    private FareReceipt fareReceipt;

    public Ride(
            int id,
            Rider rider,
            double distance
    ) {
        this.id = id;
        this.rider = rider;
        this.distance = distance;
        this.status = RideStatus.REQUESTED;
    }

    public int getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getDistance() {
        return distance;
    }

    public RideStatus getStatus() {
        return status;
    }

    public FareReceipt getFareReceipt() {
        return fareReceipt;
    }

    public void assignDriver(Driver driver) {
        this.driver = driver;
        this.status = RideStatus.ASSIGNED;
    }

    public void completeRide(FareReceipt fareReceipt) {
        this.fareReceipt = fareReceipt;
        this.status = RideStatus.COMPLETED;
    }

    public void cancelRide() {
        this.status = RideStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", rider=" + rider.getName() +
                ", driver=" + (driver != null ? driver.getName() : "Not Assigned") +
                ", distance=" + distance +
                ", status=" + status +
                ", fareReceipt=" + fareReceipt +
                '}';
    }
}
