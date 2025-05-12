package com.course.registration.repository;

import com.course.registration.model.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfo, Long> {
    Optional<AdminInfo> findByEmail(String email);
    Optional<AdminInfo> findByUsernameAndPassword(String username, String password);
}
