package com.airtribe.rideWise.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private IdGenerator() {
    }

    private static final AtomicInteger RIDER_ID = new AtomicInteger(0);

    private static final AtomicInteger DRIVER_ID = new AtomicInteger(0);

    private static final AtomicInteger RIDE_ID = new AtomicInteger(0);

    public static int generateRiderId() {
        return RIDER_ID.incrementAndGet();
    }

    public static int generateDriverId() {
        return DRIVER_ID.incrementAndGet();
    }

    public static int generateRideId() {
        return RIDE_ID.incrementAndGet();
    }
}
