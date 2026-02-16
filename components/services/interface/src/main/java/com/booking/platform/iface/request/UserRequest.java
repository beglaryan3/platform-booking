package com.booking.platform.iface.request;

import com.booking.platform.iface.enums.Role;
import com.booking.platform.iface.enums.Status;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    public static final String NAME_REGEX = "^[A-Z]+[a-z]+-*$";
    public static final String NAME_REGEX_MSG = "Name of user must be contain A-z,a-z characters";
    public static final String NAME_NULL_MSG = "Name of user cannot be null";



    @Schema(description = "Id of the user", example = "00000000 0000-0000-0000-000000000000", hidden = true)
    private UUID userId;


    @Schema(description = "Name of the user", example = "Sargis")
    @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MSG)
    @NotBlank(message = NAME_NULL_MSG)
    private String name;

    @Schema(description = "Surname of the user", example = "Beglaryan")
    private String surname;

    @Schema(description = "Email address of the user", example = "example@example.come")
    private String email;

    @Schema(description = "Password of the user", example = "Password_123")
    private String password;

    @Schema(description = "Verification code for activate user account", hidden = true)
    private String verificationCode;

    @Schema(description = "Status of the user state,allow values are ACTIVE/INACTIVE")
    private Status status;

    @Schema(description = "Role of the user,allow values are SUPER_ADMIN/ADMIN/USER")
    private Role role;
}
