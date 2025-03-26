package com.example.airbnb.model.exceptions;

public class NoAvailableRoomsException extends RuntimeException {
    public NoAvailableRoomsException(Long accommodationId) {
        super("No available rooms for accommodation ID: " + accommodationId);
    }
}