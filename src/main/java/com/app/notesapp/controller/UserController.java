package com.app.notesapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.notesapp.dto.UserLogin;
import com.app.notesapp.model.User;
import com.app.notesapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserLogin userdto)
	{
//		System.out.println("LOGIN CONTROLLER HIT");
//		userService.login(userdto.getUsername(),userdto.getPassword());
//		return "Login successful";
		
		Authentication authenticate = authenticationManager
				.authenticate(
				new UsernamePasswordAuthenticationToken(userdto.getUsername(),userdto.getPassword())
				);
		return "Logged in";
		
				
	}
	
}
