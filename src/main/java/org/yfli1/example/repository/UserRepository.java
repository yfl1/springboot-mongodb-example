package org.yfli1.example.repository;

import org.yfli1.example.entity.User;

public interface UserRepository {
    public void saveUser(User user);
    public User getUser();
}
