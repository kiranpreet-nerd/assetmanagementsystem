package com.nerdapplabs.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.nerdapplabs.model.*;

public interface UserService{

	void save(User registerUser);
	
	void saveAsset(AssetRequest requestasset, User user);
	
	void addAsset(Asset asset);

	void delete(String email);

	int update( User user);
	
    int softDelete(String email );
	
	User getUser (String email);

	List<User> list();
	
	List<User> listEmail();
	
	List<AssetRequest> listAssetsRequest(HttpSession session);
	
	List<AssetRequest> listAsset(User user);

	User findByEmail(String email);
	
	void sendEmail(String email);

	User loginUser(String email, String password);
	
	User getUserByEmail(String email);
	
	User getUserToken(String verificationToken);
	
	void createVerificationToken(User user, String token);
	 
    VerificationToken getVerificationToken(String VerificationToken);
	

}
