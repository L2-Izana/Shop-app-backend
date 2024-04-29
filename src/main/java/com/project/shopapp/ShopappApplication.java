package com.project.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ShopappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopappApplication.class, args);
    }

}
