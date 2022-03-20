package com.cg.controller;

import com.cg.model.User;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/users/list");
        List<User> users = userService.fillAllActive();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/form-login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView =new ModelAndView("/users/admin/login");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView Login( User user) {
        user.getRole();
        ModelAndView modelAndView = new ModelAndView("/users/list");
        return modelAndView;
    }

}
