package org.example.services;

import org.example.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User addUser(User user);
    User updateUser(int id, User updateUser);
}
