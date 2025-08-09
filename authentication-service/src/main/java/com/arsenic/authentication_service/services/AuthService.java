package com.arsenic.authentication_service.services;

import com.arsenic.authentication_service.models.OtpVerificationRequest;
import com.arsenic.authentication_service.models.User;
import com.arsenic.authentication_service.repository.AuthRepository;
import com.arsenic.authentication_service.utils.JwtUtil;
import com.arsenic.authentication_service.utils.OtpUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {


    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AuthRepository authRepository, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(User user){
        LocalDateTime now = LocalDateTime.now();
        user.setCreationDate(now);

        //Generate user id
        String firstTwo = user.getUsername().substring(0,2).toUpperCase();
        String month = String.format("%02d",now.getMonthValue());
        String year = String.format("%02d",now.getYear() %100);

        long totalUsers = authRepository.count();
        String userNumber = String.format("%02d", totalUsers+1);

        //Final ID
        String userId = firstTwo+month+year+userNumber;
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String otp = OtpUtil.generateOTP();
        user.setStatus("PENDING_VERIFICATION");
        user.setOtpCode(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        User savedUser = authRepository.save(user);

        sendOtpToMobile(savedUser.getPhoneNumber(), otp);

        return savedUser;
    }

    private void sendOtpToMobile(String phoneNumber, String otp) {
        System.out.println("Sending OTP on Mobile Number: "+phoneNumber+": "+otp);
    }

    public String userVerification(OtpVerificationRequest request){
        User user = authRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(()-> new RuntimeException("User not found"));


            if (user.getStatus().equals("ACTIVE")){
                return "User ALready verified";
            }

            if (user.getOtpExpiry().isBefore(LocalDateTime.now())){
                return "OTP has expired";
            }

            if (!user.getOtpCode().equals(request.getOtp())) {
                return "Invalid OTP";
            }

            user.setStatus("ACTIVE");
            user.setOtpCode(null);
            user.setOtpExpiry(null);
            authRepository.save(user);


        return "User verified successfully";

    }

    public String login(String login, String password){
        User user = authRepository.findByUsername(login)
                .or(() -> authRepository.findByPhoneNumber(login))
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!"ACTIVE".equals(user.getStatus())){
            throw new RuntimeException("Please verify account first!");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }



}
