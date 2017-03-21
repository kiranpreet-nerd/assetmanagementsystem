package com.nerdapplabs.service;

import org.springframework.validation.Errors;

import com.nerdapplabs.model.*;

public interface UserService {
        
	    void save(RegisterUser registeruser);
        void delete(String email);
        RegisterUser findByEmail(String email);
		void validateLogin(RegisterUser registeruser, Errors error);
		void validateRegister(RegisterUser registeruser, Errors error);
}
