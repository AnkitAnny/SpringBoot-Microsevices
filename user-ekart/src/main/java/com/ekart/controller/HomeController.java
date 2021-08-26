package com.ekart.controller;

import com.ekart.VO.Product;
import com.ekart.service.UserServiceLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final UserServiceLayer serviceLayer;

    //@ResponseBody
    @GetMapping({"/","/home"})
    public String home(Model m){
        m.addAttribute("product",new Product()); // for search form
        m.addAttribute("products",serviceLayer.getAllProduct());
        return "home";
    }

    @PostMapping("/")
    public String home(Product p, Model m){
        System.out.println("searching...."+p.getItemName());
        m.addAttribute("products",serviceLayer.getProductByName(p.getItemName()));
        return "home";
    }

}
