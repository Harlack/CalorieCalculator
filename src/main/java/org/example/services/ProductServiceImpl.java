package org.example.services;

import org.example.entity.Product;
import org.example.entity.ProductDto;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCalories(product.getCalories());
            productDto.setTotalFat(product.getTotalFat());
            productDto.setTotalCarbohydrates(product.getTotalCarbohydrates());
            productDto.setSodium(product.getSodium());
            productDto.setProtein(product.getProtein());
            productDto.setCholesterol(product.getCholesterol());

            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    @Override
    public void addProduct(Product product) {
        try {
            if (productRepository.findByName(product.getName()) != null) {
                throw new RuntimeException("Product name already exists");
            } else {
                productRepository.save(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);

            if (optionalProduct.isPresent()) {
                Product existingProduct = optionalProduct.get();
                if (!existingProduct.getName().equals(updatedProduct.getName()) &&
                        productRepository.findByName(updatedProduct.getName()) != null) {
                    throw new RuntimeException("Product name already exists");
                }

                existingProduct.setName(updatedProduct.getName());
                existingProduct.setCalories(updatedProduct.getCalories());
                existingProduct.setTotalFat(updatedProduct.getTotalFat());
                existingProduct.setTotalCarbohydrates(updatedProduct.getTotalCarbohydrates());
                existingProduct.setSodium(updatedProduct.getSodium());
                existingProduct.setProtein(updatedProduct.getProtein());
                existingProduct.setCholesterol(updatedProduct.getCholesterol());

                return productRepository.save(existingProduct);
            } else {
                throw new RuntimeException("Product not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product getProductById(int id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product deleteProductById(int id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                productRepository.deleteById(id);
                return optionalProduct.get();
            } else {
                throw new RuntimeException("Product not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product getDetailsProductByName(String name) {
        try {
            Product product = productRepository.findByName(name);
            if (product != null) {
                return product;
            } else {
                throw new RuntimeException("Product not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Map<String, Integer> calculateNutrients(List<Product> products) {
        Map<String, Integer> sumValues = new HashMap<>();
        Integer sumCalories = 0, sumTotalFat = 0, sumCholesterol = 0, sumSodium = 0, sumTotalCarbs = 0, sumProtein = 0;

        for (Product product : products) {
            sumCalories += product.getCalories();
            sumTotalFat += product.getTotalFat();
            sumCholesterol += product.getCholesterol();
            sumSodium += product.getSodium();
            sumTotalCarbs += product.getTotalCarbohydrates();
            sumProtein += product.getProtein();
        }

        sumValues.put("sumCalories", sumCalories);
        sumValues.put("sumTotalFat", sumTotalFat);
        sumValues.put("sumCholesterol", sumCholesterol);
        sumValues.put("sumSodium", sumSodium);
        sumValues.put("sumTotalCarbs", sumTotalCarbs);
        sumValues.put("sumProtein", sumProtein);

        return sumValues;
    }

    @Override
    public void deleteProductFromUserProductList(Product product) {
        try {
            productRepository.deleteUserProductListByProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
