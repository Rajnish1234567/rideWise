package com.airtribe.rideWise;

import com.airtribe.rideWise.model.*;
import com.airtribe.rideWise.service.DriverService;
import com.airtribe.rideWise.service.RideService;
import com.airtribe.rideWise.service.RiderService;
import com.airtribe.rideWise.strategy.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner;
    private final RiderService riderService;
    private final DriverService driverService;
    private final RideService rideService;

    public Main() {
        scanner = new Scanner(System.in);

        riderService = new RiderService();
        driverService = new DriverService();

        RideMatchingStrategy matchingStrategy = new NearestDriverStrategy();

        FareStrategy defaultFareStrategy = new DefaultFareStrategy();
        FareStrategy fareStrategy = new PeakHourFareStrategy(defaultFareStrategy, 1.25);

        rideService = new RideService(matchingStrategy, fareStrategy, driverService);
    }

    public static void main(String[] args) {
        Main application = new Main();
        application.start();
    }

    private void start() {

        System.out.println("\n====================================");
        System.out.println("        Welcome to RideWise");
        System.out.println("======================================");

        boolean running = true;

        while (running) {
            printMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addRider();
                        break;

                    case 2:
                        addDriver();
                        break;

                    case 3:
                        viewAvailableDrivers();
                        break;

                    case 4:
                        requestRide();
                        break;

                    case 5:
                        completeRide();
                        break;

                    case 6:
                        viewRides();
                        break;

                    case 7:
                        cancelRide();
                        break;

                    case 8:
                        running = false;
                        System.out.println("Thank you for using RideWise!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void printMenu() {

        System.out.println();
        System.out.println("------------- MENU -------------");
        System.out.println("1. Add Rider");
        System.out.println("2. Add Driver");
        System.out.println("3. View Available Drivers");
        System.out.println("4. Request Ride");
        System.out.println("5. Complete Ride");
        System.out.println("6. View Rides");
        System.out.println("7. Cancel Ride");
        System.out.println("8. Exit");
        System.out.println("--------------------------------");
    }

    private void addRider() {
        String name = readString("Enter rider name: ");
        String location = readString("Enter rider location: ");
        Rider rider = riderService.registerRider(name, location);

        System.out.println("Rider registered successfully.");
        System.out.println(rider);
    }

    private void addDriver() {
        String name = readString("Enter driver name: ");
        String location = readString("Enter driver location: ");

        VehicleType vehicleType = readVehicleType();
        Driver driver = driverService.registerDriver(name, location, vehicleType);

        System.out.println("Driver registered successfully.");
        System.out.println(driver);
    }

    private void viewAvailableDrivers() {
        List<Driver> drivers = driverService.getAvailableDrivers();

        if (drivers.isEmpty()) {
            System.out.println("No drivers are currently available.");
            return;
        }

        System.out.println("\nAvailable Drivers:");
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    private void requestRide() {
        int riderId = readInt("Enter rider ID: ");

        Rider rider = riderService.getRiderById(riderId)
                        .orElseThrow(() -> new IllegalArgumentException("Rider not found."));

        double distance = readDouble("Enter distance in KM: ");

        Ride ride = rideService.requestRide(rider, distance);

        System.out.println("\nRide requested successfully.");
        System.out.println("Ride ID: " + ride.getId());
        System.out.println("Assigned Driver: " + ride.getDriver().getName());
        System.out.println("Vehicle: " + ride.getDriver().getVehicleType());
        System.out.println("Distance: " + ride.getDistance() + " KM");
        System.out.println("Status: " + ride.getStatus());
    }

    private void completeRide() {
        int rideId = readInt("Enter ride ID: ");

        FareReceipt receipt = rideService.completeRide(rideId);

        System.out.println("\nRide completed successfully.");
        System.out.println("Ride ID: " + receipt.getRideId());
        System.out.println("Final Fare: ₹" + String.format("%.2f", receipt.getAmount()));
        System.out.println("Generated At: " + receipt.getGeneratedAt());
    }

    private void cancelRide() {
        int rideId = readInt("Enter ride ID: ");
        rideService.cancelRide(rideId);

        System.out.println("Ride cancelled successfully.");
    }

    private void viewRides() {
        List<Ride> rides = rideService.getAllRides();

        if (rides.isEmpty()) {
            System.out.println("No rides available.");
            return;
        }

        System.out.println("\n========== ALL RIDES ==========");

        for (Ride ride : rides) {
            System.out.println("Ride ID       : " + ride.getId());
            System.out.println("Rider         : " + ride.getRider().getName());
            System.out.println("Driver        : " + (ride.getDriver() != null ? ride.getDriver().getName() : "Not Assigned"));
            System.out.println("Distance      : " + ride.getDistance() + " KM");
            System.out.println("Status        : " + ride.getStatus());

            if (ride.getFareReceipt() != null) {
                System.out.println("Fare          : ₹" + String.format("%.2f", ride.getFareReceipt().getAmount()));
            }

            System.out.println("-------------------------------");
        }
    }

    private VehicleType readVehicleType() {
        while (true) {
            System.out.println("Select vehicle type:");
            System.out.println("1. BIKE");
            System.out.println("2. AUTO");
            System.out.println("3. CAR");

            int choice = readInt("Choice: ");

            switch (choice) {
                case 1:
                    return VehicleType.BIKE;
                case 2:
                    return VehicleType.AUTO;
                case 3:
                    return VehicleType.CAR;
                default:
                    System.out.println("Invalid vehicle type.");
            }
        }
    }

    private String readString(String message) {
        while (true) {
            System.out.print(message);

            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (value <= 0) {
                    System.out.println("Value must be greater than zero.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}