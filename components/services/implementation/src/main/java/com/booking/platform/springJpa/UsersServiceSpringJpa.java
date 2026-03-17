package com.booking.platform.springJpa;

import com.booking.platform.entity.UserEntity;
import com.booking.platform.helper.UserHelper;
import com.booking.platform.iface.enums.Status;
import com.booking.platform.iface.exceptions.userException.UserAlreadyExistException;
import com.booking.platform.iface.exceptions.userException.UserApiException;
import com.booking.platform.iface.exceptions.userException.UserBadRequestException;
import com.booking.platform.iface.exceptions.userException.UserNotFoundException;
import com.booking.platform.iface.request.user.AbstractUserRequest;
import com.booking.platform.iface.request.user.CreateUserRequest;
import com.booking.platform.iface.request.user.UpdateUserRequest;
import com.booking.platform.iface.response.UserResponse;
import com.booking.platform.iface.service.UserService;
import com.booking.platform.repository.CompanyRepository;
import com.booking.platform.repository.UserRepository;
import com.booking.platform.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceSpringJpa implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserHelper userHelper;


    @Override
    public List<UserResponse> getAllUsers() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(UserEntity::toUserResponse)
                    .toList();
        } catch (Exception e) {
            throw new UserApiException("Error occurred while getting users", e);
        }
    }

    @Override
    public UserResponse createUser(CreateUserRequest userRequest) {

        validateDuplicates(null, userRequest);
        String verifyCode = TokenGenerator.generateVerificationCode();

        userRequest.setVerificationCode(TokenGenerator.generateVerificationCode());
        userRequest.setStatus(Status.INACTIVE);
        userRequest.setVerificationCode(verifyCode);


        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = new UserEntity(userRequest);
        validateCompany(userRequest.getCompanyId());
        try {
            return removeHiddenFields(userHelper.saveUser(userEntity)).toUserResponse();
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                throw e;
            }
            throw new UserApiException("Error occurred while saving user", e);
        }
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        try {
            return getUserEntityById(userId).toUserResponse();
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                throw e;
            }
            throw new UserApiException("Error occurred while getting user", e);
        }
    }

    @Override
    public UserResponse updateUser(UUID userId, UpdateUserRequest userRequest) {
        if (userRequest.getUserId() != null && !userId.equals(userRequest.getUserId())) {
            throw new UserBadRequestException("ID of path and ID of request are different");
        }
        UserEntity userEntity = getUserEntityById(userId);
        validateDuplicates(userId, userRequest);
        userRequest.setVerificationCode(userEntity.getVerificationCode());
        userRequest.setUserId(userId);
        validateCompany(userRequest.getCompanyId());
        try {

            return userRepository.save(new UserEntity(userRequest, userEntity.getPassword())).toUserResponse();
        } catch (Exception e) {
            throw new UserApiException("Error occurred while updating user", e);
        }
    }

    @Override
    public void deleteUser(UUID userId) {
        getUserEntityById(userId);
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new UserApiException("Error occurred while deleting user", e);
        }
    }

    private UserEntity getUserEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }


    private void validateDuplicates(UUID userId, AbstractUserRequest userRequest) {
        UserEntity userEntity;
        try {
            if (userId != null) {
                userEntity = userRepository.findByEmailAndUserIdNot(userRequest.getEmail(), userId);
            } else {
                userEntity = userRepository.findByEmail(userRequest.getEmail());
            }
        } catch (Exception e) {
            throw new UserApiException("Error occurred while getting user", e);
        }

        if (userEntity != null) {
            throw new UserAlreadyExistException("User already exist with email " + userRequest.getEmail());
        }
    }

    private UserEntity removeHiddenFields(UserEntity userEntity) {
        userEntity.setVerificationCode(null);
        userEntity.setStatus(null);
        return userEntity;
    }

    private void validateCompany(UUID companyId) {
        try {
            if (companyId != null) {
                companyRepository.findById(companyId)
                        .orElseThrow(() -> new UserNotFoundException("Company not found with id " + companyId));
            }
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                throw e;
            }
            throw new UserApiException("Error occurred while validating company", e);
        }
    }


}
