package com.course.registration.service.impl;

import com.course.registration.model.CourseRegistration;
import com.course.registration.repository.CourseRegistrationRepository;
import com.course.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public CourseRegistration save(CourseRegistration registration) {
        return courseRegistrationRepository.save(registration);
    }
}
