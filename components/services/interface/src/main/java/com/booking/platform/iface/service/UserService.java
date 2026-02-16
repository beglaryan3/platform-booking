package com.booking.platform.iface.service;

import com.booking.platform.iface.request.UserRequest;
import com.booking.platform.iface.response.UserResponse;

import java.util.List;

public interface UserService {
    /**
     * Method for getting all users
     * @return UserResponse containing user data
     * */
    List<UserResponse> getAllUsers();

    /**
     * Method for creating new user
     * @param userRequest to be created
     * @return created user
     */
    UserResponse createUser(UserRequest userRequest);
}
