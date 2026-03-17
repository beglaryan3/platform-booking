package com.booking.platform.iface.exceptions.userException;

import com.booking.platform.ForbiddenException;

public class UserForbiddenException extends ForbiddenException {

    public UserForbiddenException(String errorMessage) {
        super(errorMessage);
    }
}
