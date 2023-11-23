package org.example;

import lombok.AllArgsConstructor;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class CalorieCalculatorApplication{

    private UserRepository userRepository;
    public static void main(String[] args) {
    SpringApplication.run(CalorieCalculatorApplication.class,args);
    }


}
