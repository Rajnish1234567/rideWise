package com.airtribe.rideWise.strategy;

import com.airtribe.rideWise.model.Driver;
import com.airtribe.rideWise.model.Rider;

import java.util.List;

public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
}
