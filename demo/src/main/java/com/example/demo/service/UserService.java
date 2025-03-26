package com.example.demo.service;

import com.example.demo.DTO.UpdateUserDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.model.User;

public interface UserService {
    User createUser(UserDTO userDTO);
    User updateUser(Long id, UpdateUserDTO updateUserDTO);
    User getUserById(Long id);
    User getUserByUsername(String username);
}