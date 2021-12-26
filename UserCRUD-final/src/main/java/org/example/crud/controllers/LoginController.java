package org.example.crud.controllers;


import org.example.crud.models.User;
import org.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "Log/login";
    }

    @PostMapping("/login/create")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/login";
    }//done


    @GetMapping("/registration")
    public String newUser(@ModelAttribute("user") User user) {

        return "Log/registration";
    }//done

}
