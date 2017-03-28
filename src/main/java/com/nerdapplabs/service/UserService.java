package com.nerdapplabs.service;

import java.util.List;

import com.nerdapplabs.model.*;

public interface UserService{

	void save(User registerUser);

	void delete(String email);

	void Update(User user);
	
	User edit (String email);

	List<User> list();

	User findByEmail(String email);

	User loginUser(String email, String password);

}
