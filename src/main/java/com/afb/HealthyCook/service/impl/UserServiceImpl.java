package com.afb.HealthyCook.service.impl;

import com.afb.HealthyCook.domain.model.User;
import com.afb.HealthyCook.domain.repository.UsersRepository;
import com.afb.HealthyCook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User save(User user) {
        Date date = new Date();
        user.setDateCreated(date);
        return this.usersRepository.save(user);
    }
}
