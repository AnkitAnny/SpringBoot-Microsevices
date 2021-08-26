package com.ekart.security.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ekart.VO.Product;
import com.ekart.entity.User;
import com.ekart.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Component
public class CustomUserDetails implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println(username);
		User user = repo.findByEmail(username);
		System.out.println(user);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(user);

	}



}
