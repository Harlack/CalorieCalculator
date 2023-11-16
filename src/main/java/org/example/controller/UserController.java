package org.example.controller;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    private List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    private Optional<User> getUserById(@PathVariable int id){
        return userRepository.findById(id);
    }
    @PostMapping
    private User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    private User updateUser(@PathVariable int id, @RequestBody User updateUser){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setId(updateUser.getId());
            user.get().setUsername(updateUser.getUsername());
            user.get().setPassword(updateUser.getPassword());
        }
        return null;
    }
}
