package com.airtribe.rideWise.exception;

public class InvalidInputException extends IllegalArgumentException{
    public InvalidInputException(String s) {
        super(s);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
