package org.yfli1.example.service.Impl;

import org.yfli1.example.repository.UserRepository;
import org.yfli1.example.entity.User;
import org.yfli1.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }
}
