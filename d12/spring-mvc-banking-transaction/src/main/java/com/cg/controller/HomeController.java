package com.cg.controller;


import com.cg.model.Person;
import com.cg.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IPersonService personService;

    @GetMapping
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");

        List<Person> personList = personService.findAll();

        modelAndView.addObject("personList", personList);

        return modelAndView;
    }


}
