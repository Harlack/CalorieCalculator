package org.example.services;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User updateUser) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setId(updateUser.getId());
            user.get().setUsername(updateUser.getUsername());
            user.get().setPassword(updateUser.getPassword());
        }
        return null;
    }
}
