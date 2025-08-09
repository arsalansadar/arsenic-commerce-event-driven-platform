package com.arsenic.authentication_service.controller;

import com.arsenic.authentication_service.models.JwtResponse;
import com.arsenic.authentication_service.models.LoginRequest;
import com.arsenic.authentication_service.models.OtpVerificationRequest;
import com.arsenic.authentication_service.models.User;
import com.arsenic.authentication_service.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User registerUser = authService.registerUser(user);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody OtpVerificationRequest request){
        String userVerification = authService.userVerification(request);
        return ResponseEntity.ok(userVerification);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        String token = authService.login(request.getLogin(), request.getPassword());
        System.out.println("JWT Token: "+token);
        return ResponseEntity.ok(new JwtResponse(token));

    }





}
