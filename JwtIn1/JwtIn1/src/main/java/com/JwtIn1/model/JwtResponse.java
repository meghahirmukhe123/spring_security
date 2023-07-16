package com.JwtIn1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//this class is for we want to send token in json format thats why
public class JwtResponse {

	String token;
}
