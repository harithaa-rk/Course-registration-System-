package com.course.registration.service;

import com.course.registration.model.UserInfo;
import com.course.registration.model.CourseRegistration;
import com.course.registration.repository.UserInfoRepository;
import com.course.registration.repository.CourseRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    public UserInfo registerUser(UserInfo user) {
        Optional<UserInfo> existing = userInfoRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        user.setRole("USER"); // default role
        return userInfoRepository.save(user);
    }

    public Optional<UserInfo> getUserByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    public Optional<UserInfo> login(String username, String password) {
        System.out.println("Attempting to find user: " + username);
        Optional<UserInfo> user = userInfoRepository.findByUsername(username);
        
        if (user.isPresent()) {
            UserInfo userInfo = user.get();
            System.out.println("Found user: " + userInfo.getUsername());
            System.out.println("Comparing passwords - Input: " + password + ", Stored: " + userInfo.getPassword());
            
            if (password != null && password.equals(userInfo.getPassword())) {
                return user;
            }
        }
        
        System.out.println("Login failed for username: " + username);
        return Optional.empty();
    }

    public boolean forgotPassword(String email, String newPassword) {
        Optional<UserInfo> user = userInfoRepository.findByEmail(email);
        if (user.isPresent()) {
            UserInfo existingUser = user.get();
            existingUser.setPassword(newPassword);  // Set the new password
            userInfoRepository.save(existingUser);
            return true;
        }
        return false;
    }

    public List<CourseRegistration> getUserRegistrations(Long userId) {
        // First get the user to get their email
        Optional<UserInfo> user = userInfoRepository.findById(userId);
        if (!user.isPresent()) {
            System.out.println("No user found for userId: " + userId);
            return List.of();
        }
        String userEmail = user.get().getEmail();
        System.out.println("Fetching registrations for email: " + userEmail);
        
        // Get registrations by student email
        List<CourseRegistration> registrations = courseRegistrationRepository.findAllByStudentEmail(userEmail);
        System.out.println("Found " + registrations.size() + " registrations for email: " + userEmail);
        return registrations;
    }

    public List<CourseRegistration> getRegistrationsByEmail(String email) {
        return courseRegistrationRepository.findAllByStudentEmail(email);
    }

    public Optional<CourseRegistration> getRegistrationById(Long registrationId) {
        return courseRegistrationRepository.findById(registrationId);
    }
    
}
