package com.course.registration.controller;

import com.course.registration.model.UserInfo;
import com.course.registration.model.CourseRegistration;
import com.course.registration.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserInfo user) {
        logger.info("Registering user: {}", user.getUsername());
        userInfoService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInfo user) {
        logger.info("Login attempt - Username: {}", user.getUsername());
        try {
            return userInfoService.login(user.getUsername().trim(), user.getPassword())
                    .map(u -> {
                        logger.info("Login successful for user: {}", u.getUsername());
                        return ResponseEntity.ok(Map.of(
                            "message", "Login successful",
                            "userId", u.getId(),
                            "username", u.getUsername()
                        ));
                    })
                    .orElseGet(() -> {
                        logger.warn("Invalid credentials for user: {}", user.getUsername());
                        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
                    });
        } catch (Exception e) {
            logger.error("Login error for user {}: {}", user.getUsername(), e.getMessage());
            return ResponseEntity.status(500).body(Map.of("message", "Error during login"));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");

        logger.info("Password reset attempt for email: {}", email);
        boolean result = userInfoService.forgotPassword(email, newPassword);
        if (result) {
            logger.info("Password updated successfully for email: {}", email);
            return ResponseEntity.ok("Password updated successfully");
        } else {
            logger.warn("User not found for email: {}", email);
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/{userId}/registrations")
    public ResponseEntity<List<CourseRegistration>> getUserRegistrations(@PathVariable Long userId) {
        try {
            logger.info("Fetching registrations for userId: {}", userId);
            List<CourseRegistration> registrations = userInfoService.getUserRegistrations(userId);
            
            if (registrations.isEmpty()) {
                logger.warn("No registrations found for userId: {}", userId);
                return ResponseEntity.ok(List.of());
            }

            logger.info("Found {} registrations for userId: {}", registrations.size(), userId);
            return ResponseEntity.ok(registrations);
        } catch (Exception e) {
            logger.error("Error fetching registrations for userId {}: {}", userId, e.getMessage());
            return ResponseEntity.status(500).body(List.of());
        }
    }
}
