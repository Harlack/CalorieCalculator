package org.example.services;

import org.example.entity.Product;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            product.get().setId(updateProduct.getId());
            product.get().setName(updateProduct.getName());
            product.get().setCalories(updateProduct.getCalories());
        }
        return null;
    }
}
