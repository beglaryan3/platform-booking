package com.booking.platform.iface.request.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateCompanyRequest extends AbstractCompanyRequest {

    @Schema(description = "ID of the company", example = "00000000-0000-0000-0000-000000000000")
    private UUID companyId;
}
