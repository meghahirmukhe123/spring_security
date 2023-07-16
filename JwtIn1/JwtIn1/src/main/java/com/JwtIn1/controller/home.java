package com.JwtIn1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {
	
	@GetMapping("/home")
	public String getdata()
	{
		return "Shri Swami Samarth";
	}

}
