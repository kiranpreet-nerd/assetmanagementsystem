package com.ams.pageobject;

import java.io.IOException;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;
import com.ams.testsetup.dataReader;

@RunWith(Parameterized.class)
public class Add_UserBySuperAdmin extends SetUp{
	
	protected String userEmail;
	protected String userFirstName;
	protected String userLastName;
	protected String userDesignation;
	protected String userPassword;
	protected String userConfirmPassword;
	
	protected By adduser = By.linkText("ADD USER");
	protected By useremail = By.name("email");
	protected By firstname = By.name("firstname");
	protected By lastname = By.name("lastname");
	protected By desgnation = By.name("designation");
	protected By password = By.name("password");
	protected By confrmPassword = By.name("confirm");
	protected By addBtn = By.name("registerbutton");
	protected By role_user = By.cssSelector("input[id ='checkbox'][value ='ROLE_USER']");
	protected By table = By.cssSelector("table[class ='table table-striped']");
	protected By rows = By.tagName("tr");
	protected By cols = By.tagName("td");
	protected By logout = By.linkText("LOGOUT");
	

	public Add_UserBySuperAdmin(String userEmail,String userFirstName,String userLastName,String userDesignation,String userPassword, String userConfirmPassword) {
		this.userEmail = userEmail;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDesignation = userDesignation;
		this.userPassword = userPassword;
		this.userConfirmPassword = userConfirmPassword;		
	}
	
	@Parameterized.Parameters
	public static  Collection<String[]> getData() throws IOException {
			return dataReader.getTestData("./datasource/Add_UserBySuperAdmin.csv");	
	}	
	}