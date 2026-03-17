package com.booking.platform.iface.exceptions.companyException;

import com.booking.platform.ApiException;

public class CompanyApiException extends ApiException {
    public CompanyApiException(String message) {
        super(message);
    }

    public CompanyApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
