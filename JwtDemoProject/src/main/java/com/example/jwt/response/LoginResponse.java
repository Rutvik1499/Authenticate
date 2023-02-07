package com.example.jwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String fullName;
}
