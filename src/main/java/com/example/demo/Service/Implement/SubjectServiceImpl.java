package com.example.demo.Service.Implement;

import com.example.demo.Entity.SubjectEntity;
import com.example.demo.Repository.SubjectRepository;
import com.example.demo.Service.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        super();
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectEntity findByName(String name) {
        return subjectRepository.findByName(name);
    }
}
