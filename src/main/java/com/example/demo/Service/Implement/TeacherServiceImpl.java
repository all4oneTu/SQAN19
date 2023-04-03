package com.example.demo.Service.Implement;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Repository.TeacherRepository;
import com.example.demo.Service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        super();
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherEntity save(TeacherEntity teacherEntity) {
        return teacherRepository.save(teacherEntity);
    }

    @Override
    public TeacherEntity findByUsername(String username) {
        return teacherRepository.findByUsername(username);
    }

    @Override
    public TeacherEntity findById(Long id) {
        return teacherRepository.findById(id).get();
    }
}
