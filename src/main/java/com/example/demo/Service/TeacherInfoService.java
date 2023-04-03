package com.example.demo.Service;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.TeacherInfoEntity;

public interface TeacherInfoService {
    TeacherInfoEntity save(TeacherInfoEntity teacherInfoEntity);
    TeacherInfoEntity find (TeacherEntity teacherEntity);
    TeacherInfoEntity findById(Long id);
    TeacherInfoEntity getTeacherInfoEntitiesById(Long id);
    TeacherInfoEntity getTeacherInfoEntitiesByTeacherEntity(TeacherEntity teacherEntity);
}
