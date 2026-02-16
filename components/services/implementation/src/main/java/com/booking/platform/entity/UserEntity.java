package com.booking.platform.entity;

import com.booking.platform.iface.constants.DatabaseConstants;
import com.booking.platform.iface.enums.Role;
import com.booking.platform.iface.enums.Status;
import com.booking.platform.iface.request.UserRequest;
import com.booking.platform.iface.response.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.USERS_TABLE_NAME,schema = DatabaseConstants.SCHEMA_NAME)

public class UserEntity {

    @Id
    @UuidGenerator
    @Column(name = "user_id", nullable = false ,unique = true)
    private UUID userId;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private  String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public  UserEntity(UserRequest userRequest){
        this.userId = userRequest.getUserId();
        this.name = userRequest.getName();
        this.surname = userRequest.getSurname();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.verificationCode = userRequest.getVerificationCode();
        this.role = userRequest.getRole();
        this.status = userRequest.getStatus();

    }

    public UserResponse toUserResponse() {
        return  UserResponse
                .builder()
                .userId(userId)
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .verificationCode(verificationCode)
                .status(status)
                .role(role)
                .build();
    }


}
