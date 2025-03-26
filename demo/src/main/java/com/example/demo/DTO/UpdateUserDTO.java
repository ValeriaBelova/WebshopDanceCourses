package com.example.demo.DTO;

import java.util.List;

public class UpdateUserDTO {
    private String username;
    private String email;
    private List<String> roles;

    //Getters och Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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