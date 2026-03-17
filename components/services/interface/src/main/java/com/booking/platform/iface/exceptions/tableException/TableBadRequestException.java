package com.booking.platform.iface.exceptions.tableException;

import com.booking.platform.BadRequestException;

public class TableBadRequestException extends BadRequestException {
    public TableBadRequestException(String message) {
        super(message);
    }
}
