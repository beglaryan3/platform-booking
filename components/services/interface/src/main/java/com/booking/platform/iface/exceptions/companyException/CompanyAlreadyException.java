package com.booking.platform.iface.exceptions.companyException;

import com.booking.platform.ResourceAlreadyExistException;

public class CompanyAlreadyException extends ResourceAlreadyExistException {
    public CompanyAlreadyException(String message) {
        super(message);
    }
}
