package com.course.registration.controller;

import com.course.registration.model.AdminInfo;
import com.course.registration.service.AdminInfoService;
import com.course.registration.dto.CourseRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    // Register Admin
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminInfo admin) {
        adminInfoService.registerAdmin(admin);
        return ResponseEntity.ok("Admin registered successfully");
    }

    // Login Admin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminInfo admin) {
        try {
            return adminInfoService.login(admin.getUsername().trim(), admin.getPassword())
                    .map(a -> {
                        return ResponseEntity.ok(Map.of(
                            "message", "Login successful",
                            "adminId", a.getId(),
                            "username", a.getUsername()
                        ));
                    })
                    .orElse(ResponseEntity.status(401).body(Map.of("message", "Invalid credentials")));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Error during login", "error", e.getMessage()));
        }
    }

    // Get all registrations
    @GetMapping("/registrations")
    public ResponseEntity<?> getAllRegistrations() {
        try {
            List<CourseRegistrationDTO> registrations = adminInfoService.getAllRegistrations();
            if (registrations.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("message", "No registrations found"));
            }
            return ResponseEntity.ok(Map.of("message", "Registrations retrieved successfully", "data", registrations));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Error retrieving registrations", "error", e.getMessage()));
        }
    }
}
