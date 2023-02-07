package com.example.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.jwt.entities.UserIdentity;

@Repository
public interface UserIdentityRepo extends JpaRepository<UserIdentity, Long>, JpaSpecificationExecutor<UserIdentity> {
	
	UserIdentity findByUsernameAndIsActiveTrue(String username);

}
