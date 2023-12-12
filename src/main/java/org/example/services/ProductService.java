package org.example.services;

import org.example.entity.Product;
import org.example.entity.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDto> getAllProducts();
    void addProduct(Product product);
    Product updateProduct(int id, Product updateProduct);
    Product getProductById(int id);
    Product deleteProductById(int id);
    Product getDetailsProductByName(String name);
    Map<String, Integer> calculateNutrients(List<Product> products);
    void deleteProductFromUserProductList(Product product);
}
