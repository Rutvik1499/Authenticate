package com.example.jwt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.entities.UserIdentity;
import com.example.jwt.model.Login;
import com.example.jwt.repo.UserIdentityRepo;
import com.example.jwt.response.LoginResponse;
import com.example.jwt.response.Response;
import com.example.jwt.security.JwtUtil;
import com.example.jwt.service.UserIdentityService;

@Service
public class UserIdentityServiceImpl implements UserIdentityService {

	@Autowired
	UserIdentityRepo userIdentityRepo;

	//@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public ResponseEntity<Object> authenticate(Login loginVo) {
		String fullName = null;
		UserIdentity login = userIdentityRepo.findByUsernameAndIsActiveTrue(loginVo.getUserName());

		if (login != null) {
			fullName = login.getFirstname() + " " + login.getLastname();

			if (passwordEncoder.matches(loginVo.getPassword(), login.getPassword())) {
				String token = jwtUtil.generateToken(login);
				return new ResponseEntity<>(new LoginResponse(token, "Bearer", login.getId(), login.getUsername(),
						login.getFirstname(), login.getLastname(), fullName), HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<>(new Response("User not found", "User not found"),
					HttpStatus.NOT_FOUND);
		}
		return null;
		
	}

}
