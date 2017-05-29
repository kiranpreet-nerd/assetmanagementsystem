package com.nerdapplabs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdapplabs.model.User;
import com.nerdapplabs.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	
	VerificationToken findByToken(String token);
	
	VerificationToken findByUser(User user);
}
