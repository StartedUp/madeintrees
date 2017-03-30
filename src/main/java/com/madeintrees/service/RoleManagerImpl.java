package com.madeintrees.service;

import com.madeintrees.dao.RoleDao;
import com.madeintrees.model.Role;
import com.madeintrees.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Prithu on 22-03-2017.
 */
@Service
@Transactional
public class RoleManagerImpl implements RoleManager{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
