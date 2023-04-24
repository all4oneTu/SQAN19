package com.example.demo.Repository;

import com.example.demo.Entity.ScheduleEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

   List<ScheduleEntity> getScheduleEntitiesByTeacherInfoEntity(TeacherInfoEntity teacherInfoEntity);
    List<ScheduleEntity> findAllByTeacherInfoEntity(TeacherInfoEntity teacherInfoEntity);
    ScheduleEntity getScheduleEntityById(Long id);
}
