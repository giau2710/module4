package com.cg.controller;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.model.form.UserForm;
import com.cg.model.status.Role;
import com.cg.service.UserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AppUtils appUtils;

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/users/admins/list");
//        List<User> userDTOs = userService.findAllActive();
//        modelAndView.addObject("users", userDTOs);
//        modelAndView.addObject("userForm", new UserForm());
        List<UserDTO> userDTOs = userService.findAllUserDTOByDeletedIsFalse();
        modelAndView.addObject("users", userDTOs);
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

    @GetMapping("/form-login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("/users/admins/login");
        modelAndView.addObject("user", new UserDTO());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView Login(@ModelAttribute UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.existsByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword())) {
            User userCheck = userService.findByUsername(userDTO.getUsername());
            if (userCheck.getRole().equals(Role.ADMIN) && !userCheck.isDeleted()) {
                modelAndView.setViewName("/index");
                List<UserDTO> users = userService.findAllUserDTOByDeletedIsFalse();
                modelAndView.addObject("users", users);
                modelAndView.addObject("messageLoginSuccessful", "Login Successful");
                modelAndView.addObject("userForm", new UserForm());
                return modelAndView;
            }
        }
        modelAndView.addObject("user", new UserDTO());
        modelAndView.addObject("message", "Wrong username or password");
        modelAndView.setViewName("/users/admins/login");
        return modelAndView;
    }

//    @GetMapping("/{id}")
//    public ModelAndView getById(@PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/users/admins/list");
//        if (user.isPresent()) {
//            modelAndView.addObject("userForm");
//        }
//        return modelAndView;
//    }

    @PostMapping("/save")
    public ModelAndView save(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponseModel("/users/admins/list", bindingResult);
        }
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
        ModelAndView modelAndView = new ModelAndView("/users/admins/list");
        List<User> users = userService.findAllActive();
        modelAndView.addObject("users", users);
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("message", "Created new admin successfully!");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView saveEdit(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponseModel("/users/admins/list", bindingResult);
        }
        ModelAndView modelAndView = new ModelAndView("/users/admins/list");

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
        List<User> users = userService.findAllActive();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
