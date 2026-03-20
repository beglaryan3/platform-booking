package com.booking.platform.iface.request.table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AbstractTableRequest {
    private List<String> tableNumbers;
    private int seatNumber;
    private String tableType;
}
