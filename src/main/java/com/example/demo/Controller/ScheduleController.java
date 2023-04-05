package com.example.demo.Controller;


import com.example.demo.Entity.ScheduleEntity;
import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import com.example.demo.Service.ScheduleService;
import com.example.demo.Service.TeacherInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class ScheduleController {

    private ScheduleService scheduleService;
    private TeacherInfoService teacherInfoService;

    private Date date = new Date();

    public ScheduleController(ScheduleService scheduleService, TeacherInfoService teacherInfoService) {
        super();
        this.scheduleService = scheduleService;
        this.teacherInfoService = teacherInfoService;
    }

    @GetMapping("/schedule")
    public String schedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        TeacherEntity teacherEntity = (TeacherEntity) session.getAttribute("teacher");
        TeacherInfoEntity teacherInfoEntity = teacherInfoService.getTeacherInfoEntitiesByTeacherEntity(teacherEntity);
        List<ScheduleEntity> schedules = scheduleService.findAllByTeacherInfoEntity(teacherInfoEntity);
        Collections.sort(schedules,
                Comparator.comparing(ScheduleEntity::getDate)
                        .thenComparing(ScheduleEntity::getTimeStart));
        model.addAttribute("username", teacherEntity.getUsername());
        if (schedules.size() == 0) {
            model.addAttribute("message", "No schedule");
        } else {
            model.addAttribute("schedules", schedules);
        }
        model.addAttribute("date", date);
        return "schedule";
    }

}
