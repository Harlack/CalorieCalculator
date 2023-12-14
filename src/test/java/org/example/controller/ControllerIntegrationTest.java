package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CalorieCalculatorApplication;
import org.example.entity.ProductDto;
import org.example.entity.User;
import org.example.entity.UserDto;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.services.ProductService;
import org.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = CalorieCalculatorApplication.class)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(
                new ProductDto(1L, "Product1", 100, 20, 30, 40, 50, 60),
                new ProductDto(2L, "Product2", 150, 30, 20, 10, 50, 30)
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }
    @Test
    public void testAddUser() throws Exception {
        UserDto user = new UserDto();
        user.setUsername("newUser");
        user.setEmail("newuser@newuser.pl");
        user.setPassword("password");

        when(userService.addUser(user)).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders.post("/register/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("newUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("newuser@newuser.pl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist()); // Ensure password is not returned in the response
    }

}
