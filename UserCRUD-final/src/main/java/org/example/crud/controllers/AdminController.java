package org.example.crud.controllers;

import org.example.crud.models.Role;
import org.example.crud.models.User;
import org.example.crud.service.RoleServiceImpl;
import org.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admins/")
public class AdminController {

//    private final UserService userService;
//
//    public AdminController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/")
//    public String ShowAllUsers(Model model) {
//        model.addAttribute("users", userService.index());
//        return "/admins/index";
//    } //done
//
//    @GetMapping("/users/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.show(id));
//        return "admins/show";
//    }//done
//
//    @GetMapping("/users/new")
//    public String newUser(@ModelAttribute("user") User user) {
//
//        return "admins/new";
//    }//done
//
//    @PostMapping("/users")
//    public String create(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/admins/";
//    }//done
//
//    @GetMapping("/users/{id}/edit")
//    public String edit( @PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.show(id));
//        return "admins/edit";
//    }//done
//
//    @PatchMapping("/users/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userService.update(id, user);
//        return "redirect:/admins/";
//    }
//
//
//    @DeleteMapping("/users/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.delete(id);
//        return "redirect:/admins/";
//    }
private final UserService userService;

@Autowired
private final RoleServiceImpl roleService;

    public AdminController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String ShowAllUsers(Model model) {
        model.addAttribute("users", userService.index());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admins/index";
    } //done

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "admins/show";
    }//done

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admins/new";
    }//done

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "admin", required = false) boolean admin,
                         @RequestParam(value = "userr", required = false) boolean userr) {

        Set<Role> roles = new HashSet<>();
        if (userr) {
            roles.add(new Role(2L, "ROLE_USER"));
        }
        if (admin) {
            roles.add(new Role(1L, "ROLE_ADMIN"));
            roles.add(new Role(2L, "ROLE_USER"));
        }
        user.setRoles(roles);

        userService.save(user);


        return "redirect:/admins/";
    }//done

    @GetMapping("/users/{id}/edit")
    public String edit( @PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.show(id));

        return "admins/edit";
    }//done

    @PatchMapping("/users-update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id,
                         @RequestParam(value = "admin", required = false) boolean admin,
                         @RequestParam(value = "userr", required = false) boolean userr) {

        Set<Role> roles = new HashSet<>();
        if (userr) {
            roles.add(new Role(2L, "ROLE_USER"));
        }
        if (admin) {
            roles.add(new Role(1L, "ROLE_ADMIN"));
            roles.add(new Role(2L, "ROLE_USER"));
        }
        user.setRoles(roles);
        userService.update(id, user);
        return "redirect:/admins/";
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admins/";
    }
}
