package com.course.registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Add this field

    @NotBlank(message = "Course name is required")
    @Column(name = "course_name", unique = true)
    private String courseName;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotBlank(message = "Duration is required")
    private String duration;
    
    @NotNull(message = "Fee is required")
    @Positive(message = "Fee must be positive")
    private Double fee;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CourseRegistration> registrations;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public Double getFee() { return fee; }
    public void setFee(Double fee) { this.fee = fee; }
    public List<CourseRegistration> getRegistrations() { return registrations; }
    public void setRegistrations(List<CourseRegistration> registrations) { this.registrations = registrations; }
}
