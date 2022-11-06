package com.vetmanagement.vetmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetmanagement.vetmanagement.dao.UserRepository;
import com.vetmanagement.vetmanagement.dojo.User;
import com.vetmanagement.vetmanagement.exception.ResourceNotFoundException;
import com.vetmanagement.vetmanagement.security.CurrentUser;
import com.vetmanagement.vetmanagement.security.UserPrincipal;

@RestController
public class UserController {
	
	@Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
	
}
