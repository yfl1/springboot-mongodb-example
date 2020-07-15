package org.yfli1.example;

import org.yfli1.example.entity.User;
import org.yfli1.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test1");
        userService.saveUser(user);
    }

}
