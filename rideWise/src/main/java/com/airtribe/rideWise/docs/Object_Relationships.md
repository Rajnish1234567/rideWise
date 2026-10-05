# Object Relationships

## Rider -> Ride

Association.

A rider requests a ride.

One rider can have multiple rides.

Conceptually:

Rider 1 -------- * Ride

## Driver -> Ride

Association.

A driver can serve multiple rides over time.

Conceptually:

Driver 1 -------- * Ride

## Ride -> FareReceipt

Composition.

A fare receipt belongs to a ride.

The receipt is created when the ride is completed.

Conceptually:

Ride 1 -------- 1 FareReceipt

## RideService -> RideMatchingStrategy

Composition / Dependency.

RideService receives a matching strategy through constructor injection.

## RideService -> FareStrategy

Composition / Dependency.

RideService receives a fare strategy through constructor injection.

## Driver -> VehicleType

Association.

A driver has one vehicle type.

Possible values:

- BIKE
- AUTO
- CAR