package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

@Component
public class InitClass {
    private final RoleService roleService;
    private final UserService userService;

    public InitClass(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Transactional
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveAll(Set.of(adminRole, userRole));
        userService.save(new User("vova", "pupkin", "vova", "vova@mail.ru", 33, Set.of(adminRole)));
        userService.save(new User("vova1", "pupkin1", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        userService.save(new User("vova2", "pupkin2", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        userService.save(new User("vova3", "pupkin3", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        userService.save(new User("vova4", "pupkin4", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        userService.save(new User("vova5", "pupkin5", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        userService.save(new User("vova6", "pupkin6", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
    }
}
