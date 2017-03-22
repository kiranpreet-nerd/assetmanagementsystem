package com.nerdapplabs.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import com.nerdapplabs.dao.UserDao;
import com.nerdapplabs.model.User;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserDao userDao;

	Connection connection;

	@Override
	@Transactional
	public void save(User registerUser) {
		userDao.save(registerUser);
	}

	@Override
	@Transactional
	public void delete(String email) {
		userDao.delete(email);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public RegisterUser findByEmail(String email) { return
	 * null; }
	 */

	@Override
	@Transactional
	public void validateLogin(User registerUser, Errors error) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"select email, password from registeruser where email = '+email+' && password = '+password+'");
			while (rs.next()) {
				registerUser.setEmail("email");
				registerUser.setPassword("password");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
