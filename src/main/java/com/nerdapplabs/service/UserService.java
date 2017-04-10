package com.nerdapplabs.service;

import java.util.List;

import org.springframework.validation.Errors;

import com.nerdapplabs.model.*;

public interface UserService{

	void save(User registerUser);

	void delete(String email);

	int update( User user);
	
    int softDelete(String email );
	
	User getUser (String email);

	List<User> list();

	User findByEmail(String email);
	
	void sendEmail(String email);

	User loginUser(String email, String password);
	
	User getUserByEmail(String email);
	

}
