package com.ApiGateway.filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;

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
			
			ServerHttpRequest request=null;
			if (validator.isSecured.test(exchange.getRequest())) {
				// check it contain header or not.it it contain header then call to token
				// validation
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorisation header");
				}

				//authHeaders is a token
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
				
				//steps to send some info to service ex.user name of that user who send request etc...
			     request =exchange
				.getRequest()
				.mutate()
				.header("loggedInUser",jwtUtil.extractUsername(authHeaders) )
				.build();
				
				
				}
				catch (Exception e) {
					System.out.println("invalid access to application.....");
					throw new RuntimeException("unauthorized access to application");
				}
				

			}

			//here tell filter to get the request and build it 
			return chain.filter(exchange.mutate().request(request).build());

		});
		
	}

	public AuthenticationFilter(Class<Config> configClass) {
		super(configClass);
		
	}

	public static class Config {

	}

}
