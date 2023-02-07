package com.example.jwt.service;

import org.springframework.http.ResponseEntity;

import com.example.jwt.model.Login;

public interface UserIdentityService {

	ResponseEntity<Object> authenticate(Login login);

}
