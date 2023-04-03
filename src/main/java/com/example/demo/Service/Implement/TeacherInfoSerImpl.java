package com.example.demo.Service.Implement;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import com.example.demo.Repository.TeacherInfoRepository;
import com.example.demo.Service.TeacherInfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TeacherInfoSerImpl implements TeacherInfoService {

    private TeacherInfoRepository teacherInfoRepository;

    public TeacherInfoSerImpl(TeacherInfoRepository teacherInfoRepository) {
        super();
        this.teacherInfoRepository = teacherInfoRepository;
    }

    @Override
    public TeacherInfoEntity save(TeacherInfoEntity teacherInfoEntity) {
        return teacherInfoRepository.save(teacherInfoEntity);
    }

    @Override
    public TeacherInfoEntity find(TeacherEntity teacherEntity) {
        return teacherInfoRepository.findByTeacherEntityIn((Collection<TeacherEntity>) teacherEntity);
    }

    @Override
    public TeacherInfoEntity findById(Long id) {
        return teacherInfoRepository.findById(id).get();
    }

    @Override
    public TeacherInfoEntity getTeacherInfoEntitiesById(Long id) {
        return teacherInfoRepository.getTeacherInfoEntitiesById(id);
    }

    @Override
    public TeacherInfoEntity getTeacherInfoEntitiesByTeacherEntity(TeacherEntity teacherEntity) {
        return teacherInfoRepository.getTeacherInfoEntitiesByTeacherEntity(teacherEntity);
    }
}
