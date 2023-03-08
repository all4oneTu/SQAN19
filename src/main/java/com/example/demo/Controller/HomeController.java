package com.example.demo.Controller;

import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private TeacherService teacherService;

    public HomeController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        TeacherEntity teacherEntity = (TeacherEntity) session.getAttribute("teacher");
        System.out.println(teacherEntity.getUsername());
        model.addAttribute("username", teacherEntity.getUsername());
        return "home";
    }
}
