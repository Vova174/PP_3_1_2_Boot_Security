package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User getById(Long id);
   // User getByUsername(String username);
    User getUserByUsername(String username);
}
