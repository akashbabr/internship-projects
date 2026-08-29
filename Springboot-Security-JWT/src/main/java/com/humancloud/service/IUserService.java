package com.humancloud.service;

import com.humancloud.dto.LoginRequest;
import com.humancloud.dto.LoginResponse;
import com.humancloud.dto.RegisterRequest;

public interface IUserService 
{
	public String register(RegisterRequest request);
	public LoginResponse login(LoginRequest request);
}
