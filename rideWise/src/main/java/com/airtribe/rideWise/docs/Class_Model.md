# RideWise Class Model

## Rider

Represents a customer requesting rides.

Attributes:

- id
- name
- location

## Driver

Represents a driver who can accept rides.

Attributes:

- id
- name
- currentLocation
- vehicleType
- available
- activeRideCount

## Ride

Represents a ride requested by a rider.

Attributes:

- id
- rider
- driver
- distance
- status
- fareReceipt

## FareReceipt

Represents the fare generated after ride completion.

Attributes:

- rideId
- amount
- generatedAt

## Services

### RiderService

Responsible for rider registration and retrieval.

### DriverService

Responsible for driver registration, availability and retrieval.

### RideService

Responsible for ride orchestration.

It uses:

- RideMatchingStrategy
- FareStrategy

## Strategies

### RideMatchingStrategy

Defines driver selection behavior.

Implementations:

- NearestDriverStrategy
- LeastActiveDriverStrategy

### FareStrategy

Defines fare calculation behavior.

Implementations:

- DefaultFareStrategy
- PeakHourFareStrategy