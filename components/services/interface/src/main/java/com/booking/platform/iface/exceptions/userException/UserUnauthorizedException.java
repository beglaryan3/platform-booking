package com.booking.platform.iface.exceptions.userException;

import com.booking.platform.UnauthorizedException;

public class UserUnauthorizedException extends UnauthorizedException {

    public UserUnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
