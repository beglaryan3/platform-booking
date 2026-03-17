package com.booking.platform.helper;

import com.booking.platform.entity.UserEntity;
import com.booking.platform.repository.UserRepository;
import com.booking.platform.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserHelper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        UserEntity user = userRepository.save(userEntity);
        emailSender.sendEmail(userEntity.getEmail(), "Verification Code", "Verification code is :" + userEntity.getVerificationCode());
        return user;
    }


}
