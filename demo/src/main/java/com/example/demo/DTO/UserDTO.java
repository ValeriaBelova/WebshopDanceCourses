package com.example.demo.DTO;

import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private String email;
    private List<String> roles;  // E.g. ["ROLE_USER", "ROLE_ADMIN"]

    //Getters och Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}