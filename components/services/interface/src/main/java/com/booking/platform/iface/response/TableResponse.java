package com.booking.platform.iface.response;

import com.booking.platform.iface.enums.TableType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TableResponse(

        UUID tableId,

        String tableNumber,

        int seatSCount,

        TableType tableType,

        Boolean isFree,

        LocalDateTime bookingDate,

        LocalDateTime departureDate,
        CompanyResponse response) {


}
