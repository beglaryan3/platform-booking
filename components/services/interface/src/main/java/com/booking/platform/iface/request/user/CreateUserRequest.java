package com.booking.platform.iface.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateUserRequest extends AbstractUserRequest {


    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!_]).{8,}$";
    private static final String PASSWORD_REGEX_MSG = "Password must contain at least 8 characters, one uppercase letter," +
            " one lowercase letter, one digit and one special character (@#$%^&+=!)";

    @Schema(description = "Password of the user", example = "Password_123")
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_REGEX_MSG)
    @NotBlank(message = "Password can not be null")
    private String password;
}
