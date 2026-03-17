package com.booking.platform.controller;

import com.booking.platform.BadRequestException;
import com.booking.platform.ExceptionResponse;
import com.booking.platform.iface.constants.RoutConstants;
import com.booking.platform.iface.request.user.CreateUserRequest;
import com.booking.platform.iface.request.user.UpdateUserRequest;
import com.booking.platform.iface.response.UserResponse;
import com.booking.platform.iface.service.UserService;
import com.booking.platform.security.RequiredSuperAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Log4j2
@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping(RoutConstants.BASE_URL + "${platform.booking.version}" + RoutConstants.USERS)
public class UserController {


    @Autowired
    private UserService userService;

    @Operation(summary = "Create user with specified parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "User already exists", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while creating user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest userRequest) throws BadRequestException {
        log.info("Received request to create user: {}", userRequest);
        UserResponse user = userService.createUser(userRequest);
        log.info("Created user: {}", user);
        return user;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users returned", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting users", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @GetMapping
    public List<UserResponse> getUsers() {
        log.info("Received request to get all users");
        List<UserResponse> users = userService.getAllUsers();
        log.info("All users are returned");
        return users;
    }


    @Operation(summary = "Get user by given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @GetMapping("/{id}")
    public UserResponse getUserByid(@PathVariable("id") UUID id) {
        log.info("Received request to get user by id: {}", id);
        UserResponse user = userService.getUserById(id);
        log.info("User returned: {}", user);
        return user;
    }

    @Operation(summary = "Update user with specified parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "User already exists", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while updating user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable("id") UUID userId, @RequestBody @Valid UpdateUserRequest userRequest) {
        log.info("Received request to update user: {}", userId);
        UserResponse userResponse = userService.updateUser(userId, userRequest);
        log.info("User updated by ID: {}", userResponse.getEmail());
        return userResponse;
    }

    @RequiredSuperAdmin
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user by given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while deleting user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") UUID userId) {
        log.info("Received request to delete user: {}", userId);
        userService.deleteUser(userId);
        log.info("User deleted by ID: {}", userId);
    }
}
