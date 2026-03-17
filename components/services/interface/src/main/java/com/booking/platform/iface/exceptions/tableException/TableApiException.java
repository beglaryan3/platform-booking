package com.booking.platform.iface.exceptions.tableException;

import com.booking.platform.ApiException;

public class TableApiException extends ApiException {
    public TableApiException(String message) {
        super(message);
    }
    public TableApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
