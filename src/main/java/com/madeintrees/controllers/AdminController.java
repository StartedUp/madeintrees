package com.madeintrees.controllers;

import com.madeintrees.dao.RoleDao;
import com.madeintrees.model.User;
import com.madeintrees.service.RoleManager;
import com.madeintrees.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;

/**
 * Created by Prithu on 21-03-2017.
 */
@Controller
public class AdminController {
    @Autowired
    private UserManager userManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleManager roleManager;

    @RequestMapping(value = "/")
    public String adminRoot(@ModelAttribute("user")User user){
        System.out.println(userManager.findByEmail("admin@madeintrees.com"));
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        System.out.println("login page");
        return "userLogin";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String signupPage(){
        return "userSignup";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user")User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userManager.save(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdminPage(){
        return "adminHome";
    }
}
