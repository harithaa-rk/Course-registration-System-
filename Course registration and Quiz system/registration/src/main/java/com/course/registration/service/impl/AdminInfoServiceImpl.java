package com.course.registration.service.impl;

import com.course.registration.dto.CourseRegistrationDTO;
import com.course.registration.model.AdminInfo;
import com.course.registration.repository.AdminInfoRepository;
import com.course.registration.repository.CourseRegistrationRepository;
import com.course.registration.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoRepository adminInfoRepository;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public void registerAdmin(AdminInfo admin) {
        adminInfoRepository.save(admin);
    }

    @Override
    public Optional<AdminInfo> getAdminByEmail(String email) {
        return adminInfoRepository.findByEmail(email);
    }

    @Override
    public Optional<AdminInfo> login(String username, String password) {
        return adminInfoRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<CourseRegistrationDTO> getAllRegistrations() {
        return courseRegistrationRepository.findAll().stream()
            .map(reg -> new CourseRegistrationDTO(
                reg.getId(),
                reg.getDisplayCourseName(),
                reg.getUserName(),
                reg.getRegistrationDate()
            ))
            .collect(Collectors.toList());
    }
}