package org.example.crud.controllers;

import org.example.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {


    @GetMapping()
    public String loginPageMain(){
        return "Log/login";
    }

}
