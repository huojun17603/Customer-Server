package com.example.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModelViewController {

    public ModelAndView findProjectPage() {
        ModelAndView modelAndView = new ModelAndView("redirect:");
        return modelAndView;
    }
}
