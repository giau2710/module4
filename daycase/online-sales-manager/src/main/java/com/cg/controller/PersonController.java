package com.cg.controller;

import com.cg.model.Person;
import com.cg.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/create")
    public ModelAndView showCreate() {
ModelAndView modelAndView =new ModelAndView("/users/create");
//        Person person = new Person();
//        person.setName("John");
//        person.setAge(19);
//        person.setAddress("California");
//        person.setPhone("0612");
//
//        personService.save(person);
return modelAndView;
    }
}
