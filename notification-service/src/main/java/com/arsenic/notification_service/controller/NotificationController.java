package com.arsenic.notification_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @GetMapping("/msg")
    public String getNotificationMsg(){
        return "Hello, User, Welcome to E-commerce platform";
    }
}
