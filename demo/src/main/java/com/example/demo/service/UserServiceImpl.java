package com.example.demo.service;

import com.example.demo.DTO.UpdateUserDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;  // Repository för att interagera med databasen

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;  // För att hash lösenordet

    @Override
    public User createUser(UserDTO userDTO) {
        // Kontrollera om användaren redan finns
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new RuntimeException("Användarnamnet är redan upptaget");
        }

        // Skapa en ny användare och sätt lösenordet
        User user = new User();
        user.setUsername(userDTO.getUsername());
        //user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Hasha lösenordet
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());

        return userRepository.save(user);  // Spara användaren i databasen
    }

    @Override
    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Användare inte hittad"));

        // Uppdatera användardata
        existingUser.setUsername(updateUserDTO.getUsername());
        existingUser.setEmail(updateUserDTO.getEmail());
        existingUser.setRoles(updateUserDTO.getRoles());

        return userRepository.save(existingUser);  // Spara uppdaterad användare
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Användare inte hittad"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Användare inte hittad"));
    }
}
