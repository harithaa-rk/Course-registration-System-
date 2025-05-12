package com.course.registration.controller;

import com.course.registration.model.Course;
import com.course.registration.model.CourseRegistration;
// import com.course.registration.repository.CourseRepository;
import com.course.registration.service.CourseService;
import com.course.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // @Autowired
    // private CourseRepository courseRepository;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/list")
    public List<Course> getCoursesForList() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCourse(@RequestBody CourseRegistration registration) {
        try {
            // Validate course name from payload
            if (registration.getCourseName() == null || registration.getCourseName().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("message", "Course name is required in the payload"));
            }
            registration.setCourseName(registration.getCourseName().trim());
            registration.setRegistrationDate(LocalDateTime.now());

            // Validate student details
            if (registration.getStudentName() == null || registration.getStudentName().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("message", "Student name is required"));
            }
            if (registration.getStudentEmail() == null || registration.getStudentEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("message", "Student email is required"));
            }

            // Save registration (no Course entity lookup)
            CourseRegistration savedRegistration = registrationService.save(registration);

            return ResponseEntity.ok(Map.of("message", "Registration successful", "data", savedRegistration));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Error registering for course", "error", e.getMessage()));
        }
    }
}
