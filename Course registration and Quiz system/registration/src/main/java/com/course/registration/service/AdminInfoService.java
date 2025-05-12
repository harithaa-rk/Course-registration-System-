package com.course.registration.service;

import com.course.registration.dto.CourseRegistrationDTO;
import com.course.registration.model.AdminInfo;

import java.util.List;
import java.util.Optional;

public interface AdminInfoService {
    // Register a new admin
    void registerAdmin(AdminInfo admin);

    // Retrieve admin by email
    Optional<AdminInfo> getAdminByEmail(String email);

    // Admin login
    Optional<AdminInfo> login(String username, String password);

    // Get all course registrations
    List<CourseRegistrationDTO> getAllRegistrations();
}
