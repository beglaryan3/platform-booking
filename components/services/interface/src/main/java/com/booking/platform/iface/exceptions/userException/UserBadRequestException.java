package com.booking.platform.iface.exceptions.userException;


import com.booking.platform.BadRequestException;

public class UserBadRequestException  extends BadRequestException {
    public UserBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
