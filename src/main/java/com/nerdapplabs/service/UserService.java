package com.nerdapplabs.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.nerdapplabs.model.*;

public interface UserService {

	void save(User registerUser);

	void saveAsset(AssetRequest requestasset, User user);

	void addAsset(Asset asset);

	void addModel(NewModel newmodel);

	void addCompany(Company company);

	void addModelAccessory(ModelAccessory modelaccessory);

	void addModelConsumable(ModelConsumable modelconsumable);

	void addSupplier(Supplier supplier);

	void addStatus(Status status);

	void delete(String email);
	
	int deleteCompany(long id);
	
	int deleteSupplier(long id);
	
	int deleteAssetModel(long id);
	
	int deleteAccessoryModel(long id);
	
	int deleteConsumableModel(long id);

	int update(User user);
	
	int updatePassword(String email,String newpassword);

	int updateAsset(Asset asset, Long id);
	
	int updateAssignRequest(AssetRequest assetrequest, Long id);
	
	int updateCancelRequest(AssetRequest assetrequest, Long id);
	
	int updateCompleteRequest(AssetRequest assetrequest, Long id);

	List<Asset> uniqueAttribute();
	
	List<Asset> existedAttributes();

	List<Asset> registeredAttribute(Long id);

	int softDelete(String email);

	int deleteAssetRequest(Long id);

	int deleteAsset(Long id);

	User getUser(String email);

	Asset getAsset(Long id);

	List<User> list();

	List<User> listEmail();

	List<NewModel> listModel();

	List<Company> listCompany();

	List<ModelAccessory> listModelAccessory();

	List<ModelConsumable> listModelConsumable();

	List<Supplier> listSupplier();

	List<Status> listStatus();

	List<AssetRequest> listAssetsRequest(HttpSession session);

	List<AssetRequest> listAsset(User user);
	
	List<Asset> listAssets();

	User findByEmail(String email);

	void sendEmail(String email);

	User loginUser(String email, String password);

	User getUserByEmail(String email);

	User getUserToken(String verificationToken);

	void createVerificationToken(User user, String token);

	VerificationToken getVerificationToken(String VerificationToken);

}
