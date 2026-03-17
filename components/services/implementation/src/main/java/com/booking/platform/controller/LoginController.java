package com.booking.platform.controller;


import com.booking.platform.iface.constants.RoutConstants;
import com.booking.platform.iface.request.LoginRequest;
import com.booking.platform.iface.service.LoginService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.booking.version}/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        log.info("Received login request: {}", loginRequest.getUsername());
        String token = loginService.login(loginRequest);
        log.info("Login successful: {}", loginRequest.getUsername());
        return token;
    }

}
