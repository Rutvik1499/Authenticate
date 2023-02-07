package com.example.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.model.Login;
import com.example.jwt.service.UserIdentityService;

@RestController
public class AuthController {
	
	@Autowired
	UserIdentityService userIdentityService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> authenticate(@RequestBody Login login)
	{
		return userIdentityService.authenticate(login);
	}

}
