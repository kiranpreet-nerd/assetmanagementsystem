package com.nerdapplabs.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.nerdapplabs.dao.UserDao;
import com.nerdapplabs.model.*;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserDao userDao;

	private JdbcTemplate jdbcTemplate;

	public UserServiceImplement(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

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

	@Override
	public void update(User user, String email, Role role) {
		if (user.getEmail() != null && user.getEmail().equals(email)) {
			String sql = "UPDATE user SET firstname=?, email=?, designation=?, " + "role=? WHERE email=?";
			jdbcTemplate.update(sql, user.getFirstname(), user.getEmail(), user.getDesignation(), role.getRole());
		}

	}

	@Override
	public List<User> list() {
		String sql = "select email,firstname,designation,role from user";
		List<User> listUsers = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User aUser = new User();

				aUser.setEmail(rs.getString("email"));
				aUser.setFirstname(rs.getString("firstname"));
				aUser.setDesignation(rs.getString("designation"));
				aUser.setRole(rs.getString("role"));

				return aUser;
			}
		});
		return listUsers;
	}

}
