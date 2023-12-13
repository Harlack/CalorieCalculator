package org.example.controller;

import org.example.CalorieCalculatorApplication;
import org.example.entity.Product;
import org.example.entity.ProductDto;
import org.example.repository.ProductRepository;
import org.example.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CalorieCalculatorApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class ProductControllerTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void getProductDetails_ReturnsCorrectDetails() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");
        product.setCalories(100);
        product.setTotalFat(5);

        // Act & Assert
        assertEquals("Test Product", product.getName());
        assertEquals(100, product.getCalories());
        assertEquals(5, product.getTotalFat());
    }


    @Test
    void addProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("cos");
        product.setCalories(134);
        product.setTotalFat(5);
        product.setSodium(15);
        product.setTotalCarbohydrates(20);
        product.setProtein(25);
        product.setCholesterol(30);

        // Act
        productService.addProduct(product);
        Product savedProduct = productRepository.findById(Math.toIntExact(product.getId())).orElse(null);

        // Assert
        assertThat(savedProduct).isNotNull();
        assertEquals("cos", savedProduct.getName());
        assertEquals(134, savedProduct.getCalories());
        assertEquals(5, savedProduct.getTotalFat());
        assertEquals(15, savedProduct.getSodium());
        assertEquals(20, savedProduct.getTotalCarbohydrates());
        assertEquals(25, savedProduct.getProtein());
        assertEquals(30, savedProduct.getCholesterol());
        productRepository.delete(savedProduct);
    }

}
