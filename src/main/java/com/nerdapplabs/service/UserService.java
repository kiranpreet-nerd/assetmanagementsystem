package com.nerdapplabs.service;

import org.springframework.validation.Errors;

import com.nerdapplabs.model.*;

public interface UserService {

	void save(User registerUser);

	void delete(String email);

	// RegisterUser findByEmail(String email);
	void validateLogin(User registerUser, Errors error);
}
