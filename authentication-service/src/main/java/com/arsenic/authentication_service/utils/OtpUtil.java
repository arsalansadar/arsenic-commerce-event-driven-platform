package com.arsenic.authentication_service.utils;

import java.security.SecureRandom;

public class OtpUtil {
    private static final SecureRandom random = new SecureRandom();

    public static String generateOTP(){
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}
