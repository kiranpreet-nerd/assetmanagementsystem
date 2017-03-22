package com.nerdapplabs.dao;

import org.springframework.data.repository.CrudRepository;

import com.nerdapplabs.model.RegisterUser;

public interface UserDao extends CrudRepository<RegisterUser , String>{
	
            //public RegisterUser findByEmail(String email);
		
	}

