package com.nerdapplabs.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.nerdapplabs.dao.*;
import com.nerdapplabs.model.*;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AssetDao assetDao;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private JavaMailSender mailService;

	@Autowired
	private VerificationTokenRepository tokenRepository;

	int count = 0;

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
	public void saveAsset(AssetRequest requestasset, User user) {
		String sql = "INSERT INTO request_asset (assetname,assettype,quantity,reason,date,email,requestmode) VALUES ('"
				+ requestasset.getAssetname() + "','" + requestasset.getAssettype() + "','" + requestasset.getQuantity()
				+ "','" + requestasset.getReason() + "','" + requestasset.getRequestdate() + "','" + user.getEmail()
				+ "',1)";
		jdbcTemplate.update(sql);
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
		if (user != null && user.getPassword().equals(password) && user.getStatus() == 1) {
			return user;
		}
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE user SET firstname='" + user.getFirstname() + "',lastname='" + user.getLastname()
				+ "', designation='" + user.getDesignation() + "', role='" + user.getRole() + "' WHERE email ='"
				+ user.getEmail() + "'";
		return jdbcTemplate.update(sql);
	}

	public int softDelete(String email) {
		String sql = "UPDATE user SET status = 0 WHERE email = '" + email + "'";
		return jdbcTemplate.update(sql);
	}

	public User getUser(String email) {
		return userDao.findOne(email);
	}
	

	@Override
	public List<User> list() {
		String sql = "SELECT email,firstname,designation,role,lastname FROM user WHERE status = 1";
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

	@Override
	public void sendEmail(String email) {
		User user = this.findByEmail(email);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject("Recovery Password");
		message.setText("hello " + user.getFirstname() + " your recovery password is " + user.getPassword());
		try {
			mailService.send(message);
			System.out.println("mail sent");
		} catch (MailException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public User getUserToken(String verificationToken) {
		User user = tokenRepository.findByToken(verificationToken).getUser();
		return user;
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);

	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}
	
	@Override
	public List<AssetRequest> listAsset(User user) {
		String sql = "SELECT r.email,r.assetname,r.assettype,r.reason,r.quantity,r.date FROM request_asset r WHERE r.email = '"
				+ user.getEmail() + "' && r.requestmode = 1";
		List<AssetRequest> listAssets = jdbcTemplate.query(sql, new RowMapper<AssetRequest>() {

			@Override
			public AssetRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
				AssetRequest asset = new AssetRequest();
			
				asset.setAssetname(rs.getString("assetname"));
				asset.setAssettype(rs.getString("assettype"));
				asset.setReason(rs.getString("reason"));
				asset.setRequestdate(rs.getString("date"));
				asset.setQuantity(rs.getString("quantity"));

				return asset;
			}
		});
		return listAssets;
	}

	@Override
	public List<User> listEmail() {
		String sql = "SELECT email, firstname , lastname FROM user";
		List<User> listEmail = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setEmail(rs.getString("email"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				return user;
			}
		});
		return listEmail;

	}

	@Override
	public List<AssetRequest> listAssetsRequest(HttpSession session) {

		String sql = "SELECT id, assettype,assetname,quantity FROM request_asset  WHERE date = CURDATE() && email = '"
				+ session.getAttribute("email") + "' && requestmode = 1";
		List<AssetRequest> listAssetsRequest = jdbcTemplate.query(sql, new RowMapper<AssetRequest>() {

			@Override
			public AssetRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
				AssetRequest assetRequest = new AssetRequest();

				assetRequest.setAssettype(rs.getString("assettype"));
				assetRequest.setAssetname(rs.getString("assetname"));
				assetRequest.setQuantity(rs.getString("quantity"));
				assetRequest.setId(Long.parseLong(rs.getString("id")));
				return assetRequest;
			}
		});
		return listAssetsRequest;
	}

	
	@Override
	public void addModel(NewModel newmodel) {
		String sql = "INSERT INTO model(model) VALUES ('"+newmodel.getModel()+"')";
		jdbcTemplate.update(sql);
		
	}

	@Override
	public void addSupplier(Supplier supplier) {
		String sql = "INSERT INTO supplier(supplier) VALUES ('"+supplier.getSupplier()+"')";
		jdbcTemplate.update(sql);
		
		
	}

	@Override
	public void addStatus(Status status) {
		String sql = "INSERT INTO status(status) VALUES ('"+status.getStatus()+"')";
		jdbcTemplate.update(sql);
		
		
	}

	@Override
	public List<NewModel> listModel() {
		String sql = "SELECT model FROM model";
		List<NewModel> listmodel = jdbcTemplate.query(sql, new RowMapper<NewModel>() {

			@Override
			public NewModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				NewModel newmodel = new NewModel();

				newmodel.setModel(rs.getString("model"));
				return newmodel;
			}
		});
		return listmodel;
	}

	@Override
	public List<Asset> listAssets() {
		String sql = "SELECT id,company,tag,model,status,serialnumber,purchasedate,supplier,ordernumber,purchasecost,warranty,quantity,totalcost,suppliercontact,assettype FROM asset where assetmode = 1";
		List<Asset> listAssets = jdbcTemplate.query(sql, new RowMapper<Asset>() {

			@Override
			public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
				Asset asset = new Asset();
                
				asset.setId(rs.getLong("id"));
				asset.setCompany(rs.getString("company"));
				asset.setAssettype(rs.getString("assettype"));
				asset.setTag(rs.getString("tag"));
				asset.setModel(rs.getString("model"));
				asset.setStatus(rs.getString("status"));
				asset.setSerialnumber(rs.getString("serialnumber"));
				asset.setPurchasedate(rs.getString("purchasedate"));
				asset.setSupplier(rs.getString("supplier"));
				asset.setSuppliercontact(rs.getString("suppliercontact"));
				asset.setOrdernumber(rs.getString("ordernumber"));
				asset.setPurchasecost(rs.getString("purchasecost"));
				asset.setWarranty(rs.getString("warranty"));
				asset.setQuantity(rs.getString("quantity"));
				asset.setTotalcost(rs.getString("totalcost"));
				return asset;
			}
		});
		return listAssets;
	}

	@Override
	public List<Supplier> listSupplier() {
		String sql = "SELECT supplier FROM supplier";
		List<Supplier> listSupplier = jdbcTemplate.query(sql, new RowMapper<Supplier>() {

			@Override
			public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
				Supplier supplier = new Supplier();

				supplier.setSupplier(rs.getString("supplier"));
				return supplier;
			}
		});
		return listSupplier;
	}

	@Override
	public List<Status> listStatus() {
		String sql = "SELECT status FROM status";
		List<Status> listStatus = jdbcTemplate.query(sql, new RowMapper<Status>() {

			@Override
			public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
			Status status = new Status();

				status.setStatus(rs.getString("status"));
				return status;
			}
		});
		return listStatus;
	}

	@Override
	public void addModelAccessory(ModelAccessory modelaccessory) {
		String sql = "INSERT INTO modelaccessory(model) VALUES ('"+modelaccessory.getModel()+"')";
		jdbcTemplate.update(sql);
		
	}

	@Override
	public void addModelConsumable(ModelConsumable modelconsumable) {
		String sql = "INSERT INTO modelconsumable(model) VALUES ('"+modelconsumable.getModel()+"')";
		jdbcTemplate.update(sql);
		
	}

	@Override
	public List<ModelAccessory> listModelAccessory() {
		String sql = "SELECT model FROM modelaccessory";
		List<ModelAccessory> listModel = jdbcTemplate.query(sql, new RowMapper<ModelAccessory>() {

			@Override
			public ModelAccessory mapRow(ResultSet rs, int rowNum) throws SQLException {
				ModelAccessory modelaccessory = new ModelAccessory();

				modelaccessory.setModel(rs.getString("model"));
				return modelaccessory;
			}
		});
		return listModel;
	}

	@Override
	public List<ModelConsumable> listModelConsumable() {
		String sql = "SELECT model FROM modelconsumable";
		List<ModelConsumable> listModel = jdbcTemplate.query(sql, new RowMapper<ModelConsumable>() {

			@Override
			public ModelConsumable mapRow(ResultSet rs, int rowNum) throws SQLException {
				ModelConsumable modelconsumable = new ModelConsumable();

				modelconsumable.setModel(rs.getString("model"));
				return modelconsumable;
			}
		});
		return listModel;
	}

	@Override
	public int deleteAsset(Long id) {
		String sql = "UPDATE asset SET assetmode = 0 WHERE id = '" + id + "'";
		return jdbcTemplate.update(sql);
	}

	@Override
	public Asset getAsset(Long id) {  
		return assetDao.findOne(id);
	}

	@Override
	public int updateAsset(Asset asset,Long id) {
		String sql = "UPDATE asset SET company='" + asset.getCompany() + "',tag='" + asset.getTag()
		+ "', model='" + asset.getModel() + "', status='" + asset.getStatus()  + "', serialnumber='" + asset.getSerialnumber() + "',purchasedate='" + asset.getPurchasedate() + "',supplier='" + asset.getSupplier()  + "',ordernumber='" + asset.getOrdernumber() + "',purchasecost='" + asset.getPurchasecost()  + "',warranty='" + asset.getWarranty()  + "', quantity='" + asset.getQuantity()  + "',suppliercontact='" + asset.getSuppliercontact()  + "',assettype='" + asset.getAssettype()  + "',totalcost='" + asset.getTotalcost()  + "'WHERE id='"
		+ id + "'";
return jdbcTemplate.update(sql);
	}
	
	@Override
	public void addAsset(Asset asset) {
		String sql = "INSERT INTO asset (company,tag,model,status,serialnumber,purchasedate,supplier,ordernumber,purchasecost,warranty,quantity,suppliercontact,assettype,assetmode,totalcost) VALUES ('"
				+ asset.getCompany() + "','" + asset.getTag() + "','" + asset.getModel()
				+ "','" + asset.getStatus() + "','" + asset.getSerialnumber() + "','" + asset.getPurchasedate()
				+ "','" + asset.getSupplier() + "','" + asset.getOrdernumber() + "','" + asset.getPurchasecost() + "','" + asset.getWarranty() 
				+ "','" + asset.getQuantity() + "','"+asset.getSuppliercontact()+"','"+asset.getAssettype()+"',1,'"+asset.getTotalcost()+"')";
		jdbcTemplate.update(sql);
		
	}

	@Override
	public List<Asset> uniqueAttribute() {
		String sql = "SELECT serialnumber,tag,ordernumber FROM asset";
		List<Asset> uniqueNumbersList = jdbcTemplate.query(sql, new RowMapper<Asset>() {

			@Override
			public Asset mapRow(ResultSet rs, int rowNum) throws SQLException {
				Asset asset = new Asset();

				asset.setSerialnumber(rs.getString("serialnumber"));
				asset.setTag(rs.getString("tag"));
				asset.setOrdernumber(rs.getString("ordernumber"));
				return asset;
			}
		});
		return uniqueNumbersList;
	}

	@Override
	public void addCompany(Company company) {
		String sql = "INSERT INTO company(company) VALUES ('"+company.getCompany()+"')";
		jdbcTemplate.update(sql);
		
	}

	@Override
	public List<Company> listCompany() {
		String sql = "SELECT company FROM company";
		List<Company> listCompany = jdbcTemplate.query(sql, new RowMapper<Company>() {

			@Override
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
				Company company = new Company();

				company.setCompany(rs.getString("company"));
				return company;
			}
		});
		return listCompany;
	}

	@Override
	public int deleteAssetRequest(Long id) {
		String sql = "UPDATE request_asset SET requestmode = 0 WHERE id = '" + id + "'";
		return jdbcTemplate.update(sql);
	}
}
