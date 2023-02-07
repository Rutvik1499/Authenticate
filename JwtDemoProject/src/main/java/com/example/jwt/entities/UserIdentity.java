package com.example.jwt.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public abstract class UserIdentity implements UserDetails,Serializable {
	
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String username;

	private boolean isActive;

	private String firstname;

	private String lastname;

	@Column(unique = true)
	private String email;

	private String gender;

	@JsonIgnore
	private String password;

	private String salt;

	@JsonIgnore
	@Column(length = 1000)
	private String token;

	private LocalDateTime lastAccessTime;

	private Integer numberOfTries = 0;

	@Column(nullable = true)
	private boolean accountNonLocked;
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
