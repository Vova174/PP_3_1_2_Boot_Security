package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserDetailService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserDetailService userDetailService;
    private final UserServiceImpl userService;

    public UserController(UserDetailService userDetailService, UserServiceImpl userService) {
        this.userDetailService = userDetailService;

        this.userService = userService;
    }

    @GetMapping
    public String showUser(Model model, Principal principal){
        model.addAttribute("user",userService.findUserByName(principal.getName()));
        return "user";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getId(id));
        return "user";
    }


}
