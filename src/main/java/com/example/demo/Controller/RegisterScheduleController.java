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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegisterScheduleController {
    private ScheduleService scheduleService;
    private TeacherInfoService teacherInfoService;

    public RegisterScheduleController(ScheduleService scheduleService, TeacherInfoService teacherInfoService) {
        super();
        this.scheduleService = scheduleService;
        this.teacherInfoService = teacherInfoService;
    }

    @GetMapping("/registerSchedule")
    public String schedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        TeacherEntity teacherEntity = (TeacherEntity) session.getAttribute("teacher");
        System.out.println(session.getId() + ": " + session.getAttribute("teacher"));
        TeacherInfoEntity teacherInfoEntity = teacherInfoService.getTeacherInfoEntitiesByTeacherEntity(teacherEntity);
        List<ScheduleEntity> schedules = scheduleService.findAllByTeacherInfoEntity(teacherInfoEntity);
        model.addAttribute("schedules", schedules);
        model.addAttribute("username", teacherEntity.getUsername());
        return "registerSchedule";
    }
    @PostMapping("/registerSchedule")
    public String handleFormSubmission(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TeacherEntity teacherEntity = (TeacherEntity) session.getAttribute("teacher");
        TeacherInfoEntity teacherInfoEntity = teacherInfoService.getTeacherInfoEntitiesByTeacherEntity(teacherEntity);
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setTeacherInfoEntity(teacherInfoEntity);
        scheduleEntity.setDate(request.getParameter("date"));
        scheduleEntity.setTimeStart(request.getParameter("timeStart"));
        scheduleEntity.setLesson(request.getParameter("lesson"));
        scheduleEntity.setRoom(request.getParameter("classroom"));
        scheduleEntity.setType(request.getParameter("type"));
        scheduleEntity.setIsSubstitute("no");
        scheduleEntity.setSubjectEntity(teacherInfoEntity.getSubjectEntity());
        scheduleService.createSchedule(scheduleEntity);
        return "redirect:/registerSchedule";
    }
    @PostMapping("/registerSchedule/update")
    public String updateSchedule(@RequestParam (name = "id") String id , @ModelAttribute ScheduleEntity schedule) {
        // Lấy thông tin từ form và cập nhật thông tin schedule trong database
        // ...
        //get ScheduleEntity by id
        ScheduleEntity scheduleEntity = scheduleService.getScheduleEntityById(Long.valueOf(id));
        scheduleEntity.setIsSubstitute("yes");
        scheduleService.updateSchedule(scheduleEntity);

        return "redirect:/registerSchedule"; // Chuyển hướng về trang danh sách schedule
    }

}
