package com.humancloud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.humancloud.dto.LoginRequest;
import com.humancloud.dto.LoginResponse;
import com.humancloud.dto.RegisterRequest;
import com.humancloud.entity.UserEntity;
import com.humancloud.enums.Role;
import com.humancloud.repository.UserRepository;
import com.humancloud.security.JwtService;

@Service
public class UserServiceImpl implements IUserService 
{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public String register(RegisterRequest request) 
	{
		Optional<UserEntity> username = repo.findByUsername(request.getUsername());
		if(username.isPresent())
		{
			throw new RuntimeException("Username already exists");
		}
		
		UserEntity entity = new UserEntity();
		
		entity.setUsername(request.getUsername());
		entity.setPassword(encoder.encode(request.getPassword()));
		entity.setRole(Role.ROLE_USER);
		
		Integer id = repo.save(entity).getId();
		
		return "User registered successfully with id ::"+id;
	}
	
	@Override
	public LoginResponse login(LoginRequest request) 
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		
		UserEntity user = repo.findByUsername(request.getUsername())
		        .orElseThrow(() -> new RuntimeException("User not found"));
		
		String token = jwtService.generateToken(user.getUsername());
		
		 return new LoginResponse(
		            token,
		            "Bearer",
		            user.getUsername(),
		            user.getRole().name()
		    );
	}
}
