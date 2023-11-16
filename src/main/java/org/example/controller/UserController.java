package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.services.UserService;
import org.example.entity.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequestMapping("user")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updateUser){
        return userService.updateUser(id,updateUser);
    }
}
