package com.course.registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_registration")
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Column(name = "course_name")
    private String courseName;
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "student_email")
    @Email(message = "Invalid email format")
    private String studentEmail;
    
    private LocalDateTime registrationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public UserInfo getUser() { return user; }
    public void setUser(UserInfo user) { this.user = user; }

    // Convenience methods
    public String getDisplayCourseName() {
        return courseName != null ? courseName : "N/A";
    }
    
    public String getUserName() {
        return studentName != null ? studentName : "N/A";
    }
}
