package com.IdentityService.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IdentityService.dto.AuthRequest;
import com.IdentityService.entity.UserCredentials;
import com.IdentityService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredentials user)
	{
		return service.saveuser(user);
	}
	
	
	//to get token
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest)
	{
		org.springframework.security.core.Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		//following condition is to check only those user will get token whose data is present in database
		if(authenticate.isAuthenticated())
		{
			return service.generateToken(authRequest.getUsername());
		}
		else {
			throw new  RuntimeException("invalid access");
		}
		
		
		
	}
	
	//to validate token
	@GetMapping("/validate/{token}")
	public String validateToken(@PathVariable String token)
	{
		service.validateToken(token);
		return "Token is valid";
	}

}
