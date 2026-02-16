package com.booking.platform.springJpa;

import com.booking.platform.entity.UserEntity;
import com.booking.platform.helper.UserHelper;
import com.booking.platform.iface.enums.Status;
import com.booking.platform.iface.exceptions.userException.UserAlreadyExistException;
import com.booking.platform.iface.exceptions.userException.UserApiException;
import com.booking.platform.iface.exceptions.userException.UserBadRequestException;
import com.booking.platform.iface.request.UserRequest;
import com.booking.platform.iface.response.UserResponse;
import com.booking.platform.iface.service.UserService;
import com.booking.platform.repository.UserRepository;
import com.booking.platform.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceSpringJpa  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRequest.getUserId() != null){
            throw new UserBadRequestException("UserId must not be null");
        }

        userHelper.validateFields(userRequest);

        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByEmail(userRequest.getEmail());
        }catch (Exception e){
            throw new UserApiException("Error accorded while creating user",e);
        }

        if(userEntity != null){
            throw new UserAlreadyExistException("Email already exist with email " + userRequest.getEmail());
        }

        userRequest.setVerificationCode(TokenGenerator.generateVerificationCode());
        userRequest.setStatus(Status.INACTIVE);


        return  userRepository.save(new UserEntity(userRequest)).toUserResponse();
    }
}
