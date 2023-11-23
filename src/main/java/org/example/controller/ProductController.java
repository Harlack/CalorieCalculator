package org.example.controller;

import org.example.entity.Product;
import org.example.services.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updateProduct){
        return productService.updateProduct(id, updateProduct);
    }
}
