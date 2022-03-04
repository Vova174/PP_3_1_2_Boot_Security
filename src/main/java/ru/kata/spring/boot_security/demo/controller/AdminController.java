package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.InitClass;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    private final InitClass initClass;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, InitClass initClass) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.initClass = initClass;
    }


    @GetMapping
    public String show(Model model, Principal principal) {
        List<User> users = userServiceImpl.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", userServiceImpl.findUserByName(principal.getName()));
        return "admin";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("User") User user,
                         @RequestParam String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleServiceImpl.getRoleByName(role));
        user.setRoles(roles);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServiceImpl.getId(id));
        return "admin";
    }

    @GetMapping("/init")
    public String init() {
        initClass.init();
        return "index";
    }

    @PostMapping("delete")
    public String delete (@RequestParam Long id){
        User user = userServiceImpl.findUserById(id);
        userServiceImpl.delete(user);
        return "redirect:/admin";
    }
}
