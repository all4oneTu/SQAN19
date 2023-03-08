package com.example.demo.Service;

import com.example.demo.Entity.TeacherEntity;

public interface TeacherService {
    TeacherEntity save(TeacherEntity teacherEntity);
    TeacherEntity findByUsername(String username);
    TeacherEntity findById(Long id);
}
