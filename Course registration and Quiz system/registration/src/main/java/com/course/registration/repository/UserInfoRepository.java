package com.course.registration.repository;

import com.course.registration.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT u FROM UserInfo u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<UserInfo> findByUsername(@Param("username") String username);
    
    Optional<UserInfo> findByEmail(String email);
}
