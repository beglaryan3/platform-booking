package com.booking.platform.iface.request.user;

import com.booking.platform.iface.enums.Role;
import com.booking.platform.iface.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AbstractUserRequest {
    public static final String NAME_REGEX = "^[A-Z]+[a-z]+-*$";
    public static final String NAME_REGEX_MSG = "Name of user must be contain A-z,a-z characters";
    public static final String NAME_NULL_MSG = "Name of user can not be null";

    public static final String SURNAME_REGEX = "^[A-Z]+[a-z]+-*$";
    public static final String SURNAME_REGEX_MSG = "Surname user must be contain A-z,a-z characters";
    public static final String SURNAME_NULL_MSG = "Surname user can not be null";

    public static final String EMAIL_FORMAT_MSG = "Email must be a valid email address";
    public static final String EMAIL_NULL_MSG = "Email can not be null";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:[.'][a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,15}$";


    @Schema(description = "Name of the user", example = "Sargis")
    @Pattern(regexp = NAME_REGEX, message = NAME_REGEX_MSG)
    @NotBlank(message = NAME_NULL_MSG)
    private String name;

    @Schema(description = "Surname of the user", example = "Beglaryan")
    @Pattern(regexp = SURNAME_REGEX, message = SURNAME_REGEX_MSG)
    @NotBlank(message = SURNAME_NULL_MSG)
    private String surname;

    @Schema(description = "Email address of the user", example = "example@example.com")
    @Pattern(regexp = EMAIL_REGEX, message = EMAIL_FORMAT_MSG)
    @NotBlank(message = EMAIL_NULL_MSG)
    private String email;

    @Schema(description = "ID of user`s company", example = "00000000-0000-0000-0000-000000000000")
    private UUID companyId;

    @Schema(description = "Verification code for activate user account", hidden = true)
    private String verificationCode;

    @Schema(description = "Status of the user state,allow values are ACTIVE/INACTIVE")
    private Status status;

    @Schema(description = "Role of the user,allow values are SUPER_ADMIN/ADMIN/USER")
    private Role role;
}
