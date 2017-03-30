package com.madeintrees.service;

import com.madeintrees.model.User;

/**
 * Created by Prithu on 21-03-2017.
 */
public interface UserManager {
    public User findByEmail(String email);
    public void save(User user);
}
