package com.booking.platform.iface.request.table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AbstractTableRequest {
    private String tableNumber;
    private String tableType;
}
