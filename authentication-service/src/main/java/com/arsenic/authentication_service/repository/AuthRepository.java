package com.arsenic.authentication_service.repository;

import com.arsenic.authentication_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
