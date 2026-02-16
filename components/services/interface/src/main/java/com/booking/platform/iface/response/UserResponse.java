package com.booking.platform.iface.response;

import com.booking.platform.iface.enums.Role;
import com.booking.platform.iface.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private UUID userId;

    private String name;

    private  String surname;

    private String email;

    private String password;

    private String verificationCode;

    private Status status;

    private Role role;
}
