package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User getById(Long id);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    List<User> findAll();
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles where u.name = :username")
    User getUserByName(String username);
}
