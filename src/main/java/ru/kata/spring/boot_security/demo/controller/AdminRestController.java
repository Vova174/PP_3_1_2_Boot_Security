package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.InitClass;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    private final InitClass initClass;

    public AdminRestController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, InitClass initClass) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.initClass = initClass;
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>>  show() {
        return ResponseEntity.ok(userServiceImpl.findAll());
    }
    @PostMapping("/edit")
    public ResponseEntity<User> create(@ModelAttribute("User") User user,
                                 @RequestParam String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleServiceImpl.getRoleByName(role));
        user.setRoles(roles);
        userServiceImpl.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServiceImpl.getId(id));
        return "admin";
    }

    @GetMapping("/init")
    public String init() {
        initClass.init();
        return "index";
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Long> delete( @PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}


