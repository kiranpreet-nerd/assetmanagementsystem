package com.nerdapplabs.service;

import java.util.List;

import org.springframework.validation.Errors; 

import com.nerdapplabs.model.*;

public interface UserService{

	void save(User registerUser);
	
	void saveAsset(AssetRequest requestasset);

	void delete(String email);

	int update( User user);
	
    int softDelete(String email );
	
	User getUser (String email);

	List<User> list();
	
	List<AssetRequest> listAsset();

	User findByEmail(String email);
	
	AssetRequest findById(Long id);
	
	void sendEmail(String email);

	User loginUser(String email, String password);
	
	User getUserByEmail(String email);
	
	User getUserToken(String verificationToken);
	
	void createVerificationToken(User user, String token);
	 
    VerificationToken getVerificationToken(String VerificationToken);
	

}
