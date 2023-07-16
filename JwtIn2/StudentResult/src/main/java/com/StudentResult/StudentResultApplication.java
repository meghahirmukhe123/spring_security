package com.StudentResult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentResultApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentResultApplication.class, args);
	}

}
