package com.course.registration.dto;

import java.time.LocalDateTime;

public class CourseRegistrationDTO {
    private Long id;
    private String courseName;
    private String userName;
    private LocalDateTime registrationDate;

    public CourseRegistrationDTO(Long id, String courseName, String userName, LocalDateTime registrationDate) {
        this.id = id;
        this.courseName = courseName != null ? courseName : "N/A";
        this.userName = userName != null ? userName : "N/A";
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
