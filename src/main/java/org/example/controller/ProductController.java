package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.entity.ProductDto;
import org.example.entity.User;
import org.example.entity.UserProductList;
import org.example.services.ProductService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {
    ProductService productService;
    UserService userService;

    @GetMapping()
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            return "products";
        }

        productService.addProduct(product);
        return "redirect:/products?success";
    }
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable int id, @Valid @ModelAttribute("product") Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return "redirect:/products?updated";
    }
    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable int id){
        productService.deleteProductById(id);
       // productService.deleteProductFromUserProductList(productService.getProductById(id));
        return "redirect:/products?delete";
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }
    @GetMapping("/details/{name}")
    public Product getDetailsProductByName(@PathVariable String name){
        return productService.getDetailsProductByName(name);
    }
    @GetMapping("/addProductToUserList/{id}")
    public String addProductToUserList(@PathVariable int id, Principal principal) {

        User user = userService.getUserByEmail(principal.getName());
        UserProductList userProductList = user.getUserProductList();
        if (userProductList == null) {
            userProductList = new UserProductList();
            userProductList.setUser(user);
            user.setUserProductList(userProductList);
        }
        userProductList.getProducts().add(productService.getProductById(id));

        userService.saveUser(user);

        return "redirect:/products?added";
    }
    @GetMapping("/deleteProductFromUserList/{id}")
    public String deleteProductFromUserList(@PathVariable int id, Principal principal) {

        User user = userService.getUserByEmail(principal.getName());
        UserProductList userProductList = user.getUserProductList();
        if (userProductList == null) {
            userProductList = new UserProductList();
            userProductList.setUser(user);
            user.setUserProductList(userProductList);
        }
        userProductList.getProducts().remove(productService.getProductById(id));

        userService.saveUser(user);

        return "redirect:/calculator?delete";
    }
}
