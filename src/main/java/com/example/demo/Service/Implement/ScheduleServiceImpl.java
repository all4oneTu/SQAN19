package com.example.demo.Service.Implement;

import com.example.demo.Entity.ScheduleEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import com.example.demo.Repository.ScheduleRepository;
import com.example.demo.Service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        super();
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }

    @Override
    public List<ScheduleEntity> findAllByTeacherInfoEntity(TeacherInfoEntity teacherInfoEntity) {
        return scheduleRepository.findAllByTeacherInfoEntity(teacherInfoEntity);
    }

    @Override
    public ScheduleEntity findById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Override
    public ScheduleEntity getScheduleEntityById(Long id) {
        return scheduleRepository.getScheduleEntityById(id);
    }

    @Override
    public ScheduleEntity updateSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }


}
