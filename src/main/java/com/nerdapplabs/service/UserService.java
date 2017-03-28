package com.nerdapplabs.service;

import java.util.List;

import com.nerdapplabs.model.*;

public interface UserService{

	void save(User registerUser);

	void delete(String email);

	void update(User user, String email, Role role);

	List<User> list();

	User findByEmail(String email);

	User loginUser(String email, String password);

}
