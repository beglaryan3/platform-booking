package com.booking.platform.iface.exceptions.companyException;

import com.booking.platform.BadRequestException;

public class CompanyBadRequestException extends BadRequestException {
    public CompanyBadRequestException(String message) {
        super(message);
    }
}
