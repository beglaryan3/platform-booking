package com.booking.platform.controller;

import com.booking.platform.iface.constants.RoutConstants;
import com.booking.platform.iface.request.UserRequest;
import com.booking.platform.iface.response.UserResponse;
import com.booking.platform.iface.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.booking.version}" + RoutConstants.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        log.info("Received request to create user {}", userRequest.getEmail());
        UserResponse user = userService.createUser(userRequest);
        log.info("Created user {}", user.getEmail());
        return user;
    }
}
