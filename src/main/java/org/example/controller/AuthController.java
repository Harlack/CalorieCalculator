package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.*;
import org.example.services.ProductService;
import org.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {

    private UserService userService;
    private ProductService productService;

    public AuthController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.getUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.addUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        try {
            List<UserDto> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "users";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while fetching users.");
            return "error";
        }
    }
    @GetMapping("/products")
    public String products(Model model){
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit";
    }
    @GetMapping("/calculator")
    public String calculator(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        UserProductList userProductList = user.getUserProductList();
        if (userProductList == null) {
            userProductList = new UserProductList();
            userProductList.setUser(user);
            user.setUserProductList(userProductList);
        }
        List<Product> addedProducts = user.getUserProductList().getProducts();
        Map<String, Integer> sumValues = productService.calculateNutrients(addedProducts);

        model.addAttribute("addedProducts", addedProducts);
        model.addAttribute("sumValues", sumValues);
        return "calculator";
    }

}
