package com.booking.platform.iface.exceptions.userException;

import com.booking.platform.ResourceAlreadyExistException;

public class UserAlreadyExistException extends ResourceAlreadyExistException {
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
