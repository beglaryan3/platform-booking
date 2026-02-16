package com.booking.platform.helper;

import com.booking.platform.BadRequestException;
import com.booking.platform.iface.exceptions.userException.UserBadRequestException;
import com.booking.platform.iface.request.UserRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public static final String SURNAME_REGEX = "^[A-Z]+[a-z]+-*$";
    public static final String SURNAME_REGEX_MSG = "Surname user must be contain A-z,a-z characters";
    public static final String SURNAME_NULL_MSG = "Surname user cannot be null";

    public static final String PASSWORD_LENGTH_MSG = "Password must be at least 10 characters long";
    public static final String PASSWORD_NULL_MSG = "Password can not be null";
    public static final String PASSWORD_FORMAT_MSG = "Password must contain least 1 uppercase letter and 1 digit";

    public static final String EMAIL_FORMAT_MSG = "Email must be a valid email address";
    public static final String EMAIL_NULL_MSG = "Email can not be null";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:[.'][a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,15}$";


    public void validateFields(UserRequest userRequest) {

        if (StringUtils.isBlank(userRequest.getSurname())) {
            throw new UserBadRequestException(SURNAME_NULL_MSG) ;
        }else if(!userRequest.getSurname().matches(SURNAME_REGEX)){
            throw new UserBadRequestException(SURNAME_REGEX_MSG) ;
        }

        if (StringUtils.isBlank(userRequest.getEmail())) {
            throw new UserBadRequestException(EMAIL_NULL_MSG) ;
        }else if(!userRequest.getEmail().matches(EMAIL_REGEX)){
            throw new UserBadRequestException(EMAIL_FORMAT_MSG) ;
        }

        validatePassword(userRequest.getPassword());





    }

    public void validatePassword(String password) {
        if (StringUtils.isBlank(password)) {
            throw new UserBadRequestException(PASSWORD_NULL_MSG) ;
        }else if(password.length() < 10) {
            throw new UserBadRequestException(PASSWORD_LENGTH_MSG) ;
        }
        int countOfDigits = 0;
        int countOfUppercase = 0;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                countOfDigits++;
            } else if (Character.isUpperCase(password.charAt(i))) {
                countOfUppercase++;
            }
        }

        if (countOfDigits == 0 || countOfUppercase == 0) {
            throw new UserBadRequestException(PASSWORD_FORMAT_MSG);
        }



    }
}
