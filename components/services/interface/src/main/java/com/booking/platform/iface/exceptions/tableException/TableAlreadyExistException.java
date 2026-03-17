package com.booking.platform.iface.exceptions.tableException;

import com.booking.platform.ResourceAlreadyExistException;

public class TableAlreadyExistException extends ResourceAlreadyExistException {
    public TableAlreadyExistException(String message) {
        super(message);
    }
}
