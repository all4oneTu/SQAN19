package com.example.demo.Service;

import com.example.demo.Entity.ScheduleEntity;
import com.example.demo.Entity.TeacherInfoEntity;

import java.util.List;

public interface ScheduleService {
    ScheduleEntity createSchedule(ScheduleEntity scheduleEntity);
    List<ScheduleEntity> findAllByTeacherInfoEntity(TeacherInfoEntity teacherInfoEntity);
    ScheduleEntity findById(Long id);
    ScheduleEntity getScheduleEntityById(Long id);
    ScheduleEntity updateSchedule(ScheduleEntity scheduleEntity);
}
