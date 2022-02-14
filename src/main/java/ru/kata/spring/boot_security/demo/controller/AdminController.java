package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String show(Model model) {
        List<User> users = userServiceImpl.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("User") User user,
                         @RequestParam(required=false) String roleAdmin,@RequestParam(required=false) String roleUser) {
        Set<Role> roles = new HashSet<>();

        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        }
        if(roleUser != null && roleUser.equals("ROLE_USER")) {
            roles.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        }
        user.setRoles(roles);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServiceImpl.getId(id));
        return "edit";
    }

    @GetMapping("/init")
    public String init(){
        userServiceImpl.init();
        return "index";
    }


    @PostMapping("/{id}")
    public String update(@ModelAttribute("User") User user , @RequestParam(required=false) String roleAdmin,@RequestParam(required=false) String roleUser) {
        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        }
        if(roleUser != null && roleUser.equals("ROLE_USER")) {
            roles.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        }
        user.setRoles(roles);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @PostMapping("delete/{id}")
    public String delete(@ModelAttribute("user") User user) {
        userServiceImpl.delete(user);
        return "redirect:/admin";
    }
}

