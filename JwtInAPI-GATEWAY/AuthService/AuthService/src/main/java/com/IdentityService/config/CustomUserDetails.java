package com.IdentityService.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.IdentityService.entity.UserCredentials;


//as in CustomUserDetailsService return type is userDetails but we are storing findByName in Optional form
//so to convert optional to userDetails we have used this class
public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;
	
	
	//constucotor using feild of above 2 feilds i.e. username and password
	public CustomUserDetails(UserCredentials userCredentials) {
		super();
		this.username = userCredentials.getName();
		this.password = userCredentials.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
