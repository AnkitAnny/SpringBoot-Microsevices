package com.ekart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("product-service")
    public String productService(){
        return "product-service is down...";
    }

    @GetMapping("buyer-service")
    public String userService(){
        return "buyer-service is down...";
    }
}
