package com.madeintrees.repository;

import com.madeintrees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Prithu on 27-03-2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
