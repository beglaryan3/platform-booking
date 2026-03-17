package com.booking.platform.security;

import com.booking.platform.entity.UserEntity;
import com.booking.platform.iface.exceptions.userException.UserApiException;
import com.booking.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;

        try {
            userEntity = userRepository.findByEmail(username);
        } catch (Exception e) {
            throw new UserApiException("Error while trying to load user by username", e);
        }
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .roles(String.valueOf(userEntity.getRole()))
                .authorities(new ArrayList<>())
                .build();
    }
}
