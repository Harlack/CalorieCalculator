package org.example.services;

import com.mysql.cj.log.Log;
import org.example.CalorieCalculatorApplication;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CalorieCalculatorApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
class ProductServiceImplTest {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getDetailsProductByName_ProductFound_ReturnsProduct() {

        String productName = "TestProduct";
        Product mockProduct = new Product();
        mockProduct.setName(productName);

        when(productRepository.findByName(productName)).thenReturn(mockProduct);

        Product result = productService.getDetailsProductByName(productName);

        assertNotNull(result);
        assertEquals(productName, result.getName());
    }
    @Test
    void getAllProducts() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductServiceImpl(productRepository);
        Assert.notNull(productService.getAllProducts(), "List of products is null");

    }

    @Test
    void getProductById_ProductFound_ReturnsProduct() {
        int productId = 1;
        Product mockProduct = new Product();
        mockProduct.setId((long) productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Product result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId().intValue());
    }
}