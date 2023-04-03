package com.example.demo.Repository;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TeacherInfoRepository extends JpaRepository<TeacherInfoEntity, Long> {
    TeacherInfoEntity findByTeacherEntityIn(Collection<TeacherEntity> teacherEntity);
    Optional<TeacherInfoEntity> findById (Long id);

    TeacherInfoEntity getTeacherInfoEntitiesById(Long id);
    TeacherInfoEntity getTeacherInfoEntitiesByTeacherEntity(TeacherEntity teacherEntity);
}