package org.example.services;

import org.example.entity.Product;
import org.example.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(int id, Product updateProduct);
    Product getProductById(int id);
    Product deleteProductById(int id);
    Product getDetailsProductByName(String name);
}
