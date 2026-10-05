package com.airtribe.rideWise.exception;

public class NoDriverFoundException extends RuntimeException{
    public NoDriverFoundException(String message) {
        super(message);
    }

    public NoDriverFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
