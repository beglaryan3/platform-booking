package com.booking.platform.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {
    public static String generateVerificationCode(){
        return RandomStringUtils.random(6,true,true);
    }
}
