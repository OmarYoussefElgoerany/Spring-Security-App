package com.security.spring_security.service;

import com.security.spring_security.config.SecurityConfig;
import com.security.spring_security.dao.UserRepo;
import com.security.spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepo.findAll();
    }
    public User Create(User user){
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
       // bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println(user.getPassword());
        userRepo.save(user);
        return user;
    }
}
