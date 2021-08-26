package com.ekart.service;

import com.ekart.entity.Product;
import com.ekart.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepo repo;

    public List<Product> getAllProduct() {

        return repo.findAll();
    }

    public Optional<Product> getProductById(int pid) {
        return repo.findById(pid);
    }

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getProductByName(String pName) {
        System.out.println("by name..");
        return repo.findByItemName(pName);
    }
}
