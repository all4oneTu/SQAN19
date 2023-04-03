package com.example.demo.Controller;

import com.example.demo.Entity.SubjectEntity;
import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import com.example.demo.Service.SubjectService;
import com.example.demo.Service.TeacherInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherInfoController {

    private TeacherInfoService teacherInfoService;
    private SubjectService subjectService;

    public TeacherInfoController(TeacherInfoService teacherInfoService, SubjectService subjectService) {
        super();
        this.teacherInfoService = teacherInfoService;
        this.subjectService = subjectService;
    }


    @GetMapping("/teacherInfo")
    public String teacherInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        TeacherEntity teacherEntity = (TeacherEntity) session.getAttribute("teacher");
        model.addAttribute("username", teacherEntity.getUsername());
        return "teacherInfo";
    }

    @PostMapping("/teacherInfo")
    public String handleFormSubmission(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        TeacherEntity teacherEntity = (TeacherEntity) session.getAttribute("teacher");
        System.out.println(teacherEntity.getId());
        //find teacher Info with teacherEntity
        TeacherInfoEntity teacherInfoEntity = teacherInfoService.getTeacherInfoEntitiesByTeacherEntity(teacherEntity);
        //find subject
        SubjectEntity subjectEntity = subjectService.findByName(request.getParameter("subject"));
        //save
        teacherInfoEntity.setName(request.getParameter("name"));
//        teacherInfoEntity.setSubject(request.getParameter("subject"));
        teacherInfoEntity.setEmail(request.getParameter("email"));
        teacherInfoEntity.setPhone(request.getParameter("phone"));
        teacherInfoEntity.setAddress(request.getParameter("address"));
        teacherInfoEntity.setSubjectEntity(subjectEntity);
        teacherInfoService.save(teacherInfoEntity);
        model.addAttribute("successMessage", "Lưu thành công");
        return "teacherInfo";
    }
}
