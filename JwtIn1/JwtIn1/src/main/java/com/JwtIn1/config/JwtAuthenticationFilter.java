package com.JwtIn1.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JwtIn1.helper.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter
{

	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//get header.check whether header starts from 'Bearer' then validate
	
		String requestTokenHeader=request.getHeader("Authorization");
		
		String username=null;
		String token=null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer"))
		{
			token= requestTokenHeader.substring(7);
			
			//to get username we have used try catch
			try {
				
				username=this.jwtUtil.getUsernameFromToken(token);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			//to get userdetails from userDetailService
			
			UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
			
			//for security
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				
				//to set details
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
				=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			
				
				//to set details in usernamePasswordAuthenticationToken
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
				
				//to set all this data in securityContext
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
			
		}
		
		filterChain.doFilter(request, response);
			
	
	}

}
