package org.example.controller;

import org.example.CalorieCalculatorApplication;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.services.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CalorieCalculatorApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ProductRepository productRepository;
    @Test
    void getAllProducts() {
        Product product = new Product( 1, "jablko", 134);
        Product product1 = new Product( 2, "banan", 62);
        Product product2 = new Product( 3, "pomarancz", 145);
        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);
        assertEquals(3, productRepository.findAll().size());
    }

    @Test
    void addProduct() {
        Product product = new Product( 1, "jablko", 134);
        productRepository.save(product);
        assertEquals(product, productRepository.findById(1).get());
    }

    @Test
    void updateProduct() {
        Product product = new Product( 1, "jablko", 134);
        productRepository.save(product);
        assertEquals("banan", productRepository.findById(1).get().getName());

    }
}