package com.course.registration.repository;

import com.course.registration.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    @Query("SELECT cr FROM CourseRegistration cr LEFT JOIN FETCH cr.course WHERE cr.user.id = :userId")
    List<CourseRegistration> findAllByUserId(@Param("userId") Long userId);

        @Query("SELECT cr FROM CourseRegistration cr WHERE cr.studentEmail = :studentEmail")
    List<CourseRegistration> findAllByStudentEmail(@Param("studentEmail") String studentEmail);
    boolean existsByStudentEmailAndCourseId(String studentEmail, Long courseId);
}
