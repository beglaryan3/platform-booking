package com.booking.platform.springJpa;

import com.booking.platform.entity.UserEntity;
import com.booking.platform.iface.exceptions.userException.UserApiException;
import com.booking.platform.iface.exceptions.userException.UserNotFoundException;
import com.booking.platform.iface.request.LoginRequest;
import com.booking.platform.iface.service.LoginService;
import com.booking.platform.repository.UserRepository;
import com.booking.platform.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginServiceSpringJpa implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(LoginRequest loginRequest) {
        String token = null;
        try {
            Authentication authenticate = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(), new ArrayList<>()));
            String username = authenticate.getName();
            UserEntity userEntity = userRepository.findByEmail(username);
            if (userEntity == null) {
                throw new UserNotFoundException("User not found ");
            }
            userEntity.setPassword("");
            token = jwtUtil.createToken(userEntity);
        } catch (BadCredentialsException e) {
            throw new UserApiException("Invalid username or password");
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                throw new UserApiException("Invalid username or password");
            }
            throw new UserApiException("Problem during getting token ");
        }
        return token;
    }
}
