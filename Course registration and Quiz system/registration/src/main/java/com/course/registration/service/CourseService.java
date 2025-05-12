package com.course.registration.service;

import com.course.registration.model.Course;
import com.course.registration.model.CourseRegistration;
import com.course.registration.repository.CourseRepository;
import com.course.registration.repository.CourseRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private CourseRegistrationRepository registrationRepository;

    public List<Course> getAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching courses: " + e.getMessage());
        }
    }

    public Optional<Course> getCourseById(Long id) {
        try {
            return courseRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching course: " + e.getMessage());
        }
    }

    @Transactional
    public CourseRegistration registerCourse(CourseRegistration registration) {
        // Validate input
        if (registration.getCourse() == null || registration.getStudentEmail() == null) {
            throw new IllegalArgumentException("Course and student email are required");
        }

        // Fetch managed Course entity
        Long courseId = registration.getCourse().getId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    System.err.println("Course with id " + courseId + " does not exist in the courses table.");
                    return new RuntimeException("Course not found with id: " + courseId + ". Please check if the course exists.");
                });
        registration.setCourse(course);

        // Check if student is already registered
        if (registrationRepository.existsByStudentEmailAndCourseId(
                registration.getStudentEmail(),
                courseId)) {
            throw new RuntimeException("Student already registered for this course");
        }

        try {
            registration.setRegistrationDate(LocalDateTime.now());
            return registrationRepository.save(registration);
        } catch (Exception e) {
            throw new RuntimeException("Error registering for course: " + e.getMessage());
        }
    }

    public Course saveCourse(Course course) {
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Error saving course: " + e.getMessage());
        }
    }
}
