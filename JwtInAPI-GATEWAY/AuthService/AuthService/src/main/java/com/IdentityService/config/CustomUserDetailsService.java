package com.IdentityService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.IdentityService.entity.UserCredentials;
import com.IdentityService.repo.UserCredentialsRepo;
import java.util.Optional;


@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserCredentialsRepo userCredentialsRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//to check whether user name is present in database or not
		Optional<UserCredentials> credential=userCredentialsRepo.findByname(username);
		return credential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found: "+username));
	}

}
