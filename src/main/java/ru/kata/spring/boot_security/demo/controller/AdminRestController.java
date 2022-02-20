package ru.kata.spring.boot_security.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class AdminRestController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminRestController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }


    @GetMapping("all")
    public List<User> show() {
        return userServiceImpl.findAll();
    }
    @PostMapping("edit")
    public void create(@ModelAttribute("User") User user,
                       @RequestParam String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleServiceImpl.getRoleByName(role));
        user.setRoles(roles);
        userServiceImpl.save(user);
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServiceImpl.getId(id));
        return "admin";
    }

    @GetMapping("/init")
    public String init() {
        userServiceImpl.init();
        return "index";
    }

    @PostMapping("delete/{id}")
    public void delete( @PathVariable Long id){
        userServiceImpl.deleteUserById(id);
    }
}


