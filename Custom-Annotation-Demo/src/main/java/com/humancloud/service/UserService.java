package com.humancloud.service;

import org.springframework.stereotype.Service;

import com.humancloud.annotations.LogExecution;

@Service
public class UserService
{
	@LogExecution(operation = "USER_CREATION",enabled = true)
	public void createUser(String name, int age) 
	{
	    System.out.println("Creating user: " + name + ", age: " + age);
	}
	
	public void deleteUser() 
	{
	    System.out.println("Deleting user...");
	}
}
