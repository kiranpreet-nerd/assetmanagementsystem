package com.nerdapplabs.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

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
	public User getUserByEmail(String email) {
		String sql = "select * from user where email=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public int update(User user) {
		String sql = "update user set firstname='" + user.getFirstname() + "',lastname='" + user.getLastname()
				+ "', designation='" + user.getDesignation() + "', role='" + user.getRole() + "' where email ='"
				+ user.getEmail() + "'";
		//String sql = "update user set firstname = '?', lastname = '?', email = '?', designation = '?', role = '?' where email = '?'";
		return jdbcTemplate.update(sql);
	}
	
    public int softDelete(String email) {
    	String sql = "update user set status = 0 where email = '" + email + "'";
        return jdbcTemplate.update(sql);
    }
    
    public User getUser(String email) {
    	return userDao.findOne(email);
    }
	
    
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "designation", "designation.required");
		ValidationUtils.rejectIfEmpty(errors, "role", "role.required");
	}

	@Override
	public List<User> list() {
		String sql = "select email,firstname,designation,role,lastname from user where status = 1";
		List<User> listUsers = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User aUser = new User();

				aUser.setEmail(rs.getString("email"));
				aUser.setFirstname(rs.getString("firstname"));
				aUser.setLastname(rs.getString("lastname"));
				aUser.setDesignation(rs.getString("designation"));
				aUser.setRole(rs.getString("role"));

				return aUser;
			}
		});
		return listUsers;
	}

}
