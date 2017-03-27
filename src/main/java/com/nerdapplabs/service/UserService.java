package com.nerdapplabs.service;

import com.nerdapplabs.model.*;

public interface UserService{

	void save(User registerUser);

	void delete(String email);
	
	User findByEmail(String email);
		
	User loginUser(String email, String password);
	
}
