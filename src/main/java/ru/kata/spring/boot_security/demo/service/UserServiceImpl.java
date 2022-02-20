package ru.kata.spring.boot_security.demo.service;

import org.springframework.core.annotation.Order;
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
    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Transactional
    public User getId(Long id) {
        return userDao.getById(id);
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return  userDao.findAll();
    }

    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    public User findUserByName(String name) {
        return userDao.getUserByName(name);
    }


    @Transactional
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveAll(Set.of(adminRole, userRole));
        save(new User("vova", "pupkin", "vova", "vova@mail.ru", 33, Set.of(adminRole)));
        save(new User("vova1", "pupkin1", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        save(new User("vova2", "pupkin2", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        save(new User("vova3", "pupkin3", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        save(new User("vova4", "pupkin4", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        save(new User("vova5", "pupkin5", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
        save(new User("vova6", "pupkin6", "vova", "vova1@mail.ru", 33, Set.of(userRole)));
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }
    @Transactional
    public User findUserById(Long id) {
        return userDao.getById(id);

    }
}
