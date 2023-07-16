package com.JwtIn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JwtIn1.helper.JwtUtil;
import com.JwtIn1.model.JwtRequest;
import com.JwtIn1.model.JwtResponse;

@RestController
public class JwtController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bad credentials");
		}
		
		//when password and username is right following code will execute
		
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
	
		//to generate token call JwtUtil object
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT TOKEN: "+token);
		
		
		//to convert token into json format
		return ResponseEntity.ok(new JwtResponse(token));
				
	
	}
}
