package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.UserDto;
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
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();

    }
    @GetMapping("/{email}")
    public User getUserByEmail(String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public User addUser(@RequestBody UserDto user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updateUser){
        return userService.updateUser(id,updateUser);
    }
}
