package com.brownfield.app.controller;

import com.brownfield.app.model.request.FlightSearchRequest;
import com.brownfield.app.model.request.UserRegRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private UserRegRequest userRegRequest;
    @Autowired
    private FlightSearchRequest flightSearchRequest;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("flight",flightSearchRequest);
        return "search";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",userRegRequest);
        return "register";
    }



}
