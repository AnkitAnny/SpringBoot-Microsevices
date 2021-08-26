package com.ekart.service;

import com.ekart.VO.Product;
import com.ekart.entity.AccountType;
import com.ekart.entity.User;
import com.ekart.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceLayer {

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public List<AccountType> getAccountTypeList() {
        return Arrays.asList(
                new AccountType("User"),
                new AccountType("Admin")
        );
    }

    public User saveUser(User user) {
        if(userRepo.findByEmail(user.getEmail())!=null){
            System.out.println("already user available with this id");
            return null;
        }
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        return userRepo.save(user);

    }

    @Autowired
    private RestTemplate restTemplate;

    public List<Product> getAllProduct() {
        Product[] response = restTemplate
                .getForObject("http://PRODUCT-SERVICE/product/",
                        //.getForObject("http://localhost:8001/product/",
                        Product[].class);
        System.out.println(response);
        return Arrays.asList(response);//.getProductList();
    }

    public List<Product> getProductByName(String pName) {
        Product[] response = restTemplate
                .getForObject("http://PRODUCT-SERVICE/product/search/"+pName,
                        //.getForObject("http://localhost:8001/product/search/"+pName,
                        Product[].class);
        return Arrays.asList(response);//.getProductList();
    }

}
