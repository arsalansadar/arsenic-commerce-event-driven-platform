package com.arsenic.authentication_service.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpVerificationRequest {
    private String phoneNumber;
    private String otp;


}
