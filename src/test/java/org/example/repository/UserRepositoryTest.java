package org.example.repository;

import org.example.entity.User;
import org.example.services.UserService;
import org.example.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findByEmail_UserFound_ReturnsUser() {

        String userEmail = "test@example.com";
        User mockUser = new User();
        mockUser.setEmail(userEmail);

        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        User result = userService.getUserByEmail(userEmail);

        assertNotNull(result);
        assertEquals(userEmail, result.getEmail());
    }

    @Test
    void findByEmail_UserNotFound_ReturnsNull() {

        String nonExistentUserEmail = "nonexistent@example.com";

        when(userRepository.findByEmail(nonExistentUserEmail)).thenReturn(null);

        User result = userService.getUserByEmail(nonExistentUserEmail);

        assertNull(result);
    }
}