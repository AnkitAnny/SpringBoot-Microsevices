package com.ekart.controller;

import com.ekart.entity.Product;
import com.ekart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService service;

    @GetMapping("/")
    public List<Product> getProducts() {
        return service.getAllProduct();
    }

    @PostMapping("/")
    public Product saveProduct(@RequestBody Product p) {
        return service.saveProduct(p);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductByID(@PathVariable("id") int pid) {
        return service.getProductById(pid);
    }

    @GetMapping("/search/{name}")
    public List<Product> getProductByName(@PathVariable("name") String name) {
        return service.getProductByName(name);
    }
}
