package com.booking.platform.iface.exceptions.userException;

import com.booking.platform.ApiException;

public class UserApiException  extends ApiException {
    public UserApiException(String errorMessage) {
            super(errorMessage);
    }
    public UserApiException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
