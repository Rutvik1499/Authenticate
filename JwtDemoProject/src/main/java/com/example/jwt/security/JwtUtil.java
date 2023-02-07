package com.example.jwt.security;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.jwt.entities.UserIdentity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.jjwt.secret}")
	private String secret;
	
	@Value("${jwt.jjwt.expiration}")
	private String expirationTime;

	public String generateToken(UserIdentity login) {

		Map<String, Object> claims = new HashMap<>();

		claims.put("salt", login.getSalt());
		return doGenerateToken(claims, login.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		Long expirationTimeLong = Long.parseLong(expirationTime); // in second

		final Date createdDate = new Date();
		final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes())).compact();
	}

}
