package com.booking.platform;

public abstract class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
