package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void delete(User user);
    User getId(Long id);
    List<User> findAll();

    void deleteUserById(Long id);
}