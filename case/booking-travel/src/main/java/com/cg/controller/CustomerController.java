package com.cg.controller;

import com.cg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    MailSender mailSender;

    @GetMapping("/home")
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customers/home");
        return modelAndView;
    }

    @GetMapping("/form-registration")
    public ModelAndView showFormRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("/customers/registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(User user) {
        sendEmail("huynhvangiau2710@gmail.com","huynhvangiau27100@gmail.com","Tiêu đề","nội dung");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tours/list");
        return modelAndView;
    }

    public void sendEmail(String from,String to,String subject,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }
}
