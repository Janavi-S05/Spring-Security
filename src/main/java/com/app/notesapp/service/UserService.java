package com.app.notesapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.notesapp.dto.UserLogin;
import com.app.notesapp.model.User;
import com.app.notesapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	public User createUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	public User login(String name, String password)
	{
		User user = userRepo.findByUsername(name)
				.orElseThrow(()-> new RuntimeException("Invalid username or password"));
		
		if(!encoder.matches(password,user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}
		return user;
		
	}
}