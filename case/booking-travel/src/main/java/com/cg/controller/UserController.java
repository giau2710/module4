package com.cg.controller;

import com.cg.model.User;
import com.cg.model.form.UserForm;
import com.cg.model.status.Role;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

    @GetMapping("/form-login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("/users/admin/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView Login(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.existsByUsernameAndPassword(user.getUsername(), user.getPassword())) {
            User userCheckRole = userService.findByUsername(user.getUsername());
            if (userCheckRole.getRole().equals(Role.ADMIN)) {
                modelAndView.setViewName("/index");
                List<User> users = userService.fillAllActive();
                modelAndView.addObject("users", users);
                modelAndView.addObject("userForm", new UserForm());
                return modelAndView;
            }
        }
        modelAndView.setViewName("/users/admin/login");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/list");
        if (user.isPresent()) {
            modelAndView.addObject("userForm");
        }
        return modelAndView;
    }

    @PostMapping("/save-admin")
    public ModelAndView saveAdmin(@ModelAttribute UserForm userForm, HttpServletResponse response) {
        MultipartFile multipartFile = userForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(userForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        User user = new User(userForm.getId(), userForm.getFullName(), userForm.getUsername(), userForm.getPassword(), userForm.getPhone(), userForm.getEmail(), fileName, userForm.getRole(), false, false);
        user.setRole(Role.ADMIN);
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/users/list");
        List<User> users = userService.fillAllActive();
        modelAndView.addObject("users", users);
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView saveAdminEdit(@ModelAttribute UserForm userForm) {
        ModelAndView modelAndView = new ModelAndView("/users/list");

        MultipartFile multipartFile = userForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
//        System.out.println(fileName);
        Optional<User> userCheck = userService.findById(userForm.getId());
        User user = new User(userForm.getId(), userForm.getFullName(), userForm.getUsername(), userForm.getPassword(), userForm.getPhone(), userForm.getEmail(), fileName, userForm.getRole(), false, false);
        modelAndView.addObject("userForm", new UserForm());
        if (userCheck.isPresent()) {
            if (fileName.equals("")) {
                user.setAvatar(userCheck.get().getAvatar());
            }
            modelAndView.addObject("message", "Edit user successfully!");
        } else {
            modelAndView.addObject("message", "User does not exist!");
        }
        try {
            FileCopyUtils.copy(userForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        user.setRole(Role.ADMIN);
        userService.save(user);
        List<User> users = userService.fillAllActive();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
