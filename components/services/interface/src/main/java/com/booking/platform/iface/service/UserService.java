package com.booking.platform.iface.service;

import com.booking.platform.iface.request.user.CreateUserRequest;
import com.booking.platform.iface.request.user.UpdateUserRequest;
import com.booking.platform.iface.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /**
     * Method for getting all users
     *
     * @return UserResponse containing user data
     *
     */
    List<UserResponse> getAllUsers();

    /**
     * Method for creating new user
     *
     * @param userRequest to be created
     * @return created user
     */
    UserResponse createUser(CreateUserRequest userRequest);

    /**
     * Method for getting user by getting ID
     *
     * @param userId -- ID of user
     * @return userResponse
     */
    UserResponse getUserById(UUID userId);

    /**
     * Method for updating user
     *
     * @param userId      ID of user
     * @param userRequest data to be created
     * @return UserResponse containing updated user data
     */
    UserResponse updateUser(UUID userId, UpdateUserRequest userRequest);

    /**
     * Method for deleting user
     *
     * @param userId ID of user
     */
    void deleteUser(UUID userId);

}
