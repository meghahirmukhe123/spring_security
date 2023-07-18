package com.ApiGateway.filter;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	//if request coming from following url ignore it
	public static final List<String> openEndPoints =
			List.of(
					"/auth/register",
					"/auth/token",
					"/eureka"
					
					);
	
	public Predicate<ServerHttpRequest> isSecured=
			
			request -> openEndPoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

}
