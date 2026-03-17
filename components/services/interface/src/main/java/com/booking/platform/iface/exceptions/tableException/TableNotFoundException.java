package com.booking.platform.iface.exceptions.tableException;

import com.booking.platform.EntityNotFoundException;

public class TableNotFoundException extends EntityNotFoundException {
    public TableNotFoundException(String message) {
        super(message);
    }
}
