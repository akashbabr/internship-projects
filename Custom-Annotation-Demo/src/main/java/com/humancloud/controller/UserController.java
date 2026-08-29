package com.humancloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.service.UserService;

@RestController
public class UserController 
{
	@Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public String createUser() 
    {
        userService.createUser("Raj", 25);
        return "User created";
    }
    
}