package com.madeintrees.service;

import com.madeintrees.dao.RoleDao;
import com.madeintrees.dao.UserDao;
import com.madeintrees.model.User;
import com.madeintrees.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * Created by Prithu on 21-03-2017.
 */
@Service
@Transactional
public class UserManagerImpl implements UserManager {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
