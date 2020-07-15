package org.yfli1.example.entity;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String Id;
    private String username;
    private String password;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
