package com.nerdapplabs.dao;

import org.springframework.data.repository.CrudRepository;

import com.nerdapplabs.model.User;

public interface UserDao extends CrudRepository<User , String>{
	
            public User findByEmail(String email);
            
		
	}

