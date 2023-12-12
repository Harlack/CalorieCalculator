package org.example.services;

import org.example.entity.User;
import org.example.entity.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    User getUserByEmail(String email);
    User addUser(UserDto userDto);
    User updateUser(int id, User updateUser);
    void saveUser(User user);
    void validateUserDto(UserDto userDto);

}
