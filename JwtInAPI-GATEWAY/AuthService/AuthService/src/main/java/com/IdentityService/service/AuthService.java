package com.IdentityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IdentityService.entity.UserCredentials;
import com.IdentityService.repo.UserCredentialsRepo;

@Service
public class AuthService {
	
	@Autowired
	private UserCredentialsRepo userCredentialsRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	//to save user
	public String saveuser(UserCredentials userCredentials)
	{
		userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
		userCredentialsRepo.save(userCredentials);
		return "user data added in database";
	}
	
	//generate token
	public String generateToken(String username)
	{
		return jwtService.generateToken(username);
	}

	
	//to validate token
	public void validateToken(String token)
	{
		jwtService.validateToken(token);
	}
}
