package com.booking.platform.iface.response;

import com.booking.platform.iface.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponse {

    private UUID companyId;

    private String companyName;

    private CompanyType companyType;

    private String address;

    private String phone;

    private String companyEmail;

    private String workingHours;

    private List<TableResponse> tableResponses;
}
