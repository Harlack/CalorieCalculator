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
        // Arrange
        String userEmail = "test@example.com";
        User mockUser = new User();
        mockUser.setEmail(userEmail);

        // Configure mock repository
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        // Act
        User result = userService.getUserByEmail(userEmail);

        // Assert
        assertNotNull(result);
        assertEquals(userEmail, result.getEmail());
    }

    @Test
    void findByEmail_UserNotFound_ReturnsNull() {
        // Arrange
        String nonExistentUserEmail = "nonexistent@example.com";

        // Configure mock repository to return null, simulating user not found
        when(userRepository.findByEmail(nonExistentUserEmail)).thenReturn(null);

        // Act
        User result = userService.getUserByEmail(nonExistentUserEmail);

        // Assert
        assertNull(result);
    }
}