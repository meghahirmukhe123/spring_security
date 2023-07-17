package com.ApiGateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ApiGateway.util.JwtUtil;

//AbstractGateway takes input in the form of web flux

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private JwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);

	}

	@Override
	public GatewayFilter apply(Config config) {

		return ((exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				// check it contain header or not.it it contain header then call to token
				// validation
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorisation header");
				}

				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeaders != null && authHeaders.startsWith("Bearer "))
				{
					//if above condition is true then remove spaces
					authHeaders=authHeaders.substring(7);  //this contain actual token
					
				}
				try {
					//rets call to auth service
					
					//restTemplate.getForObject("http://IDENTITY-SERVICE/validate/"+authHeaders, String.class);  //we can use this way also
				jwtUtil.validateToken(authHeaders);
				
				}
				catch (Exception e) {
					System.out.println("invalid access to application.....");
					throw new RuntimeException("unauthorized access to application");
				}
				

			}

			
			return chain.filter(exchange);

		});
		
	}

	public AuthenticationFilter(Class<Config> configClass) {
		super(configClass);
		// TODO Auto-generated constructor stub
	}

	public static class Config {

	}

}
