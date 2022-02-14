package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

@Service
public interface RoleService {
    Role getRoleByName(String name);

    void saveAll(Collection<Role> roles);

}
