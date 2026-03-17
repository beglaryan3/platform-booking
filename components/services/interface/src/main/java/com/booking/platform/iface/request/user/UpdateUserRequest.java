package com.booking.platform.iface.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateUserRequest extends AbstractUserRequest {
    @Schema(description = "Id of the user", example = "00000000 0000-0000-0000-000000000000")
    private UUID userId;
}
