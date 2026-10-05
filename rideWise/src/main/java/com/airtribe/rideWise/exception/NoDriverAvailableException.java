package com.airtribe.rideWise.exception;

public class NoDriverAvailableException extends RuntimeException{
    public NoDriverAvailableException(String message) {
        super(message);
    }

    public NoDriverAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
