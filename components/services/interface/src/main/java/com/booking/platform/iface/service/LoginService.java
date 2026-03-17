package com.booking.platform.iface.service;

import com.booking.platform.iface.request.LoginRequest;

public interface LoginService {
    /**
     * Method for user login
     * @param loginRequest contains username and password
     * @return login result
     */
    String login(LoginRequest loginRequest);
}
