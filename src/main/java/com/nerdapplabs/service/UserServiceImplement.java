package com.nerdapplabs.service;

import java.sql.Connection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	@Override
	@Transactional
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional
	public User loginUser(String email, String password) {
		User user = this.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}



