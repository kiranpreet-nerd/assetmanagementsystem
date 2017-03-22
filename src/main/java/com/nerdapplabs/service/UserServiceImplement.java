package com.nerdapplabs.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.nerdapplabs.dao.UserDao;
import com.nerdapplabs.model.RegisterUser;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserDao userDao;
	
	Connection connection;
	
	@Override
	@Transactional
	public void save(RegisterUser registerUser) {
		     userDao.save(registerUser);
    }

	@Override
	@Transactional
	public void delete(String email) {
		userDao.delete(email);
    }

	/*@Override
	@Transactional
	public RegisterUser findByEmail(String email) {
        return null;
	}*/
	
	public void validateRegister(RegisterUser registeruser, Errors error) {
		   
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "email can't be empty");
        
		if (registeruser.getEmail().length() < 6 || registeruser.getEmail().length() > 32) {
            error.rejectValue("email", "Size.registeruser.email");
        }
        
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstname", "firstname can't be empty");
        
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastname", "lastname can't be empty");
        
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "designation", "designation can't be empty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "password can't be empty");
        
        if (registeruser.getPassword().length() < 8 || registeruser.getPassword().length() > 32) {
            error.rejectValue("password", "Size.registeruser.password");
        }
       
       /* ValidationUtils.rejectIfEmptyOrWhitespace(error, "confirmpassword", "enter password again to confirm");
        if (!registeruser.getConfirmPassword().equals(registeruser.getPassword())) {
            error.rejectValue("confirmpassword", "Diff.registeruser.passwordConfirm");
        }*/
	}

	@Override
	@Transactional
	public void validateLogin(RegisterUser registerUser, Errors error) {
		 try {
			   Statement statement = connection.createStatement();
  	           ResultSet rs = statement.executeQuery("select email, password from registeruser");
  	           while(rs.next()) {
  		       registerUser.setEmail("email");
  		       registerUser.setPassword("password");
  		       
  		       } 
		      } catch(Exception e) {
	        	   e.printStackTrace();
	           }
		
	 }
}

