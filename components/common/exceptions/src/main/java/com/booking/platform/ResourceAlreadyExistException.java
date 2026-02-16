package com.booking.platform;

public abstract class ResourceAlreadyExistException extends RuntimeException {

    public ResourceAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
