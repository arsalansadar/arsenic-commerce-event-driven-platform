package com.arsenic.authentication_service.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String login;
    private String password;
}
