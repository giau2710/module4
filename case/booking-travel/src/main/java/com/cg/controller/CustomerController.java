package com.cg.controller;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.form.UserForm;
import com.cg.model.status.Role;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    MailSender mailSender;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/customers/home");
        return modelAndView;
    }

    @GetMapping("/form-registration")
    public ModelAndView showFormRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("/users/customers/registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute User user) {
//        sendEmail("huynhvangiau2710@gmail.com","huynhvangiau27100@gmail.com","Tiêu đề","nội dung");
        ModelAndView modelAndView = new ModelAndView();
        user.setRole(Role.CUSTOMER);
        userService.save(user);
        modelAndView.setViewName("/users/customers/home");
        return modelAndView;
    }

    @GetMapping("/form-login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("/users/customers/login");
        modelAndView.addObject("user", new UserDTO());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView Login(@ModelAttribute UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.existsByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword())) {
            User userCheck = userService.findByUsername(userDTO.getUsername());
            if (!userCheck.isDeleted()) {
                modelAndView.setViewName("/users/customers/home");
                modelAndView.addObject("messageLoginSuccessful", "Login Successful");
                return modelAndView;
            }
        }
        modelAndView.addObject("user", new UserDTO());
        modelAndView.addObject("message", "Wrong username or password");
        modelAndView.setViewName("/users/customers/login");
        return modelAndView;
    }

    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }
}
