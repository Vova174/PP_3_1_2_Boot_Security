package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Arrays;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserDao userdao;

    public UserDetailService(UserDao userdao) {
        this.userdao = userdao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userdao.getUserByName(username);
        return new User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}
