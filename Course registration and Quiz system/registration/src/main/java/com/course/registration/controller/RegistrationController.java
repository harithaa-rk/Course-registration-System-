// package com.course.registration.controller;

// import com.course.registration.model.Course;
// import com.course.registration.model.CourseRegistration;
// import com.course.registration.repository.CourseRepository;
// import com.course.registration.service.RegistrationService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import jakarta.validation.Valid;

// import java.time.LocalDateTime;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/registrations")
// @CrossOrigin(origins = "*")
// public class RegistrationController {
    
//     @Autowired
//     private CourseRepository courseRepository;
    
//     @Autowired
//     private RegistrationService registrationService;
    
//     // @PostMapping
//     // public ResponseEntity<?> registerCourse(@Valid @RequestBody CourseRegistration registration) {
//     //     try {
//     //         // Validate and set course details
//     //         if (registration.getCourse() == null || registration.getCourse().getId() == null) {
//     //             return ResponseEntity.badRequest()
//     //                 .body(Map.of("error", "Course ID is required"));
//     //         }
            
//     //         Course course = courseRepository.findById(registration.getCourse().getId())
//     //             .orElseThrow(() -> new RuntimeException("Course not found"));
                
//     //         // Set required fields
//     //         registration.setCourse(course);
//     //         registration.setCourseName(course.getCourseName());
//     //         registration.setRegistrationDate(LocalDateTime.now());
            
//     //         // Validate student details
//     //         if (registration.getStudentName() == null || registration.getStudentName().trim().isEmpty()) {
//     //             return ResponseEntity.badRequest()
//     //                 .body(Map.of("error", "Student name is required"));
//     //         }
//     //         if (registration.getStudentEmail() == null || registration.getStudentEmail().trim().isEmpty()) {
//     //             return ResponseEntity.badRequest()
//     //                 .body(Map.of("error", "Student email is required"));
//     //         }
            
//     //         // Save registration
//     //         CourseRegistration saved = registrationService.save(registration);
//     //         return ResponseEntity.ok(Map.of(
//     //             "message", "Registration successful",
//     //             "data", saved
//     //         ));
            
//     //     } catch (Exception e) {
//     //         return ResponseEntity.badRequest()
//     //             .body(Map.of(
//     //                 "error", "Registration failed",
//     //                 "message", e.getMessage()
//     //             ));
//     //     }
//     // }
// }
