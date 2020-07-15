package org.yfli1.example.controller;

import org.yfli1.example.entity.User;
import org.yfli1.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/example")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public String saveUser() {
        log.info("savaUser");
        User user = new User();
        user.setUsername("yfli1");
        user.setPassword("yfli1");
        userService.saveUser(user);
        return "ok";
    }

    @GetMapping("/user")
    public User getUser() {
        log.info("getUser");
        User user = userService.getUser();
        return user;
    }
}
