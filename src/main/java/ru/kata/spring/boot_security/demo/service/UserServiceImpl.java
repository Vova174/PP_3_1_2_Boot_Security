package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User getId(Long id){
        return userDao.getById(id);
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @PostConstruct
    public void init() {
        save(new User("vova", "pupkin", Set.of(new Role("ADMIN"))));
        save(new User("vova", "pupkin"));
        save(new User("valera", "puchkin"));
        save(new User("volodimir", "polyshcyk"));
        save(new User("vikor", "popov"));
        save(new User("vasya", "petrenko"));
        save(new User("vadim", "prutckyi"));
    }

}
