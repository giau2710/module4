package com.cg.utils;

import com.cg.model.dto.UserDTO;
import com.cg.model.form.UserForm;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppUtils {
    @Autowired
    UserService userService;

    public ResponseEntity<?> mapErrorToResponse(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    public ModelAndView mapErrorToResponseModel(String viewName, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        List<UserDTO> userDTOs = userService.findAllUserDTOByDeletedIsFalse();
        modelAndView.addObject("users", userDTOs);
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("errors", errors);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}