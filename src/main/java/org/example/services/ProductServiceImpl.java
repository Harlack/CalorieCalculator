package org.example.services;

import org.example.entity.Product;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        try {
            if(productRepository.findById(product.getId()).isPresent()){
                throw new Exception("Product name is already exist");
            }else {
                return productRepository.save(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        try {
            Optional<Product> product = productRepository.findById(id);

            if(product.isPresent()){
                if(product.get().getName().equals(updateProduct.getName())){
                    throw new Exception("Product name is already exist");
                }else {
                    product.get().setName(updateProduct.getName());
                    product.get().setCalories(updateProduct.getCalories());
                    return updateProduct;
                }
            }else {
                throw new Exception("Product not found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                return product.get();
            }else {
                throw new Exception("Product not found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Product deleteProductById(int id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent()){
                productRepository.deleteById(id);
                return product.get();
            }else {
                throw new Exception("Product not found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Product getDetailsProductByName(String name) {
        try {
            Product product = productRepository.findByName(name);
            if(product != null){
                return product;
            }else {
                throw new Exception("Product not found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
