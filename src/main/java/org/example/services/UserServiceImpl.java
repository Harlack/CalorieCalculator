package org.example.services;

import org.example.entity.User;
import org.example.entity.UserDto;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto();
        for (User user : users) {
                userDto.setId(user.getId());
                userDto.setEmail(user.getEmail());
                userDto.setUsername(user.getUsername());
                userDto.setPassword(user.getPassword());
                userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
