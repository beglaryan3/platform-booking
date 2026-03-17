package com.booking.platform;

public abstract class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String errorMessage){
        super(errorMessage);
    }
}
