package com.ekart.controller;

import com.ekart.VO.Product;
import com.ekart.entity.User;
import com.ekart.service.UserServiceLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserServiceLayer userServiceLayer;

    @GetMapping("/form")
    public String accountTypes(@ModelAttribute("user") User user, Model m){
        m.addAttribute("accountTypes",userServiceLayer.getAccountTypeList());
        return "registration";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           //RedirectAttributes ra,
                           Model model,
                           BindingResult br) {
        System.out.println(user);
        if(br.hasErrors()) {
            return "redirect:/form";
        }


        //ra.addFlashAttribute("savedUser", user);
        model.addAttribute("savedUser", userServiceLayer.saveUser(user));

        return "user-detail";
    }


    @GetMapping("/login-form")
    public String loginForm(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/loginClick")
    public String loginClick() {
        return "login-success";
    }

    /*@GetMapping("/profile")
    public String userProfile() {
        return "user-profile";
    }*/

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/user/login-form";
    }


    //demonstration for failure
    @ResponseBody
    @GetMapping("/products")
    public List<Product> productList(){
        return userServiceLayer.getAllProduct();
    }

}
