package com.cg.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {



    @GetMapping()
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }
    @GetMapping("/general")
    public ModelAndView showGeneral() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/general");
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCr() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/users/create");
        return modelAndView;
    }
}
