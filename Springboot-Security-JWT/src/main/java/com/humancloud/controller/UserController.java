package com.humancloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.dto.LoginRequest;
import com.humancloud.dto.LoginResponse;
import com.humancloud.dto.RegisterRequest;
import com.humancloud.service.IUserService;

@RestController
@RequestMapping("/user-api")
public class UserController 
{
	@Autowired
	private IUserService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request)
	{
		String response = service.register(request);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {

	    LoginResponse response = service.login(request);

	    return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String User() {

	    return "welcome";
	}
}
