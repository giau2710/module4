package com.cg.controller;

import com.cg.model.User;
import com.cg.model.form.UserForm;
import com.cg.model.status.Role;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/users/list");
        List<User> users = userService.fillAllActive();
        modelAndView.addObject("users", users);
        modelAndView.addObject("userForm",new UserForm());
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

    @PostMapping("/save-admin")
    public ModelAndView saveProduct(@ModelAttribute UserForm userForm, HttpServletResponse response) {
        MultipartFile multipartFile = userForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(userForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        User user = new User(userForm.getId(),userForm.getFullName(),userForm.getUsername(),userForm.getPassword(),userForm.getPhone(),userForm.getEmail(),fileName,userForm.getRole(),false,false);
        user.setRole(Role.ADMIN);
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/users/list");
        List<User> users = userService.fillAllActive();
        modelAndView.addObject("users", users);
        modelAndView.addObject("userForm",new UserForm());
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

}
