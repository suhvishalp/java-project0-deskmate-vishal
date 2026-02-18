package com.deskmate.exception;

public class DoubleBookingException extends RuntimeException {
    public DoubleBookingException(String message) { super(message); }
}
