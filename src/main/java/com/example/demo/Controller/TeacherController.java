package com.example.demo.Controller;


import com.example.demo.Entity.TeacherEntity;
import com.example.demo.Entity.TeacherInfoEntity;
import com.example.demo.Service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    @GetMapping("/login")
    public String getTeacher() {
        return "login";
    }
    @PostMapping("/login")
    public String welcomepage(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (username.equals("admin") && password.equals("admin")) {
            model.addAttribute("user", "admin");
            return "redirect:/";
        }
        if(teacherService.findByUsername(username).getPassword().equals(password)) {
            TeacherEntity teacherEntity = teacherService.findByUsername(username);
            session.setAttribute("teacher", teacherEntity);
            return "redirect:/";
        }
        else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        TeacherEntity teacherEntity = new TeacherEntity();
        TeacherInfoEntity teacherInfoEntity = new TeacherInfoEntity();
        teacherEntity.setUsername(username);
        teacherEntity.setPassword(password);
        teacherEntity.setTeacherInfoEntity(teacherInfoEntity);
        teacherInfoEntity.setTeacherEntity(teacherEntity);
        teacherService.save(teacherEntity);
        return "redirect:/login";
    }
}
