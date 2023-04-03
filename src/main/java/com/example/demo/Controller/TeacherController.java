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
    public String welcomepage(Model model, @RequestParam String username,
                              @RequestParam String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String checkUsername = checkUsername(username);

        if (checkUsername(username) != null) {
            model.addAttribute("error", checkUsername(username));
            return "login";
        }
        if (checkPassword(password) != null) {
            model.addAttribute("error", checkPassword(password));
            return "login";
        }
        if (username.equals("admin") && password.equals("admin")) {
            model.addAttribute("user", "admin");
            return "redirect:/";
        }
        if (teacherService.findByUsername(username.trim())
                .getPassword().equals(password.trim())) {
            TeacherEntity teacherEntity = teacherService.findByUsername(username);
            session.setAttribute("teacher", teacherEntity);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    private String checkPassword(String password) {
        if (password == null) {
            return "password is null";
        }
        password = password.trim();
        int length = password.length();
        if (length < 6 || length > 11) {
            return "password not reach required length";
        }
        if (password.contains("--")) {
            return "in valid password";
        }
        return null;
    }

    private String checkUsername(String username) {
        if (username == null) {
            return "username is null";
        }
        username = username.trim();
        int length = username.length();
        if (length < 3 || length > 30) {
            return "username is not reach required length";
        }
        return null;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        if (checkUsername(username) != null) {
            return "redirect:/login";
        }

        if (checkPassword(password) != null) {
            return "redirect:/login";
        }
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
