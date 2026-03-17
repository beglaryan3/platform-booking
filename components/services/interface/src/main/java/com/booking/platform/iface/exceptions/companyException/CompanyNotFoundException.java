package com.booking.platform.iface.exceptions.companyException;

import com.booking.platform.EntityNotFoundException;

public class CompanyNotFoundException extends EntityNotFoundException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
