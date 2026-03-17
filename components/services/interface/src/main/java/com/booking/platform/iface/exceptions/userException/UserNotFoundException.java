package com.booking.platform.iface.exceptions.userException;

import com.booking.platform.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
