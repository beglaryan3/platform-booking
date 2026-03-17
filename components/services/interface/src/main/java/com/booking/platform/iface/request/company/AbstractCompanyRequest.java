package com.booking.platform.iface.request.company;

import com.booking.platform.iface.enums.CompanyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCompanyRequest {
    public static final String EMAIL_FORMAT_MSG = "Email must be a valid email address";
    public static final String EMAIL_NULL_MSG = "Email can not be null";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:[.'][a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,15}$";


    @Schema(description = "Name of the company", example = "Ararat")
    private String companyName;

    @Schema(description = "Type of the company", example = "RESTAURANT")
    private CompanyType companyType;

    @Schema(description = "Address of the company", example = "Yerevan,Sayat-Nova Ave")
    private String address;

    @Schema(description = "Phone of the company", example = "+37496000000")
    private String phone;

    @Schema(description = "Email address of the company", example = "example@example.com")
    @Pattern(regexp = EMAIL_REGEX, message = EMAIL_FORMAT_MSG)
    @NotBlank(message = EMAIL_NULL_MSG)
    private String companyEmail;

    @Schema(description = "Working hours of the company", example = "Monday-Sunday,10:00-24:00")
    private String workingHours;
}
