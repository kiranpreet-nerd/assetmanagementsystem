package com.nerdapplabs.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;

	 
public class Test_Login extends SetUp {
	
	public static By emailTextBox = By.name("email");
	public static By passwordTextBox = By.name("password");
	public By loginBtn = By.name("loginbutton");
	public By errorText = By.className(".login-panel.panel.panel-default");
	 
	List<String[]> dataSource;
	static String username;
	static String password;
	
	 
	
	
	  @Test
        public void LoginFunction() throws IOException {
		 try {
			 dataSource = TestDataReader.readData("Test_Login.csv");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String[] userNameData = dataSource.get(2);
	 	username = userNameData[0];
		
	 	String[] passwordData = dataSource.get(3);
	 	password = passwordData[0];
	 	
	 	driver.findElement(emailTextBox).sendKeys(username);
	 	driver.findElement(passwordTextBox).sendKeys(password);
	 	WebElement login = driver.findElement( loginBtn);
	 	login.click();
	 	
	 	    String actual_error = driver.findElement(errorText).getText();
	 	    String expected_error = "Error Loggin in , please try again";
	 	    Assert.assertEquals("failed to loggin ", expected_error, actual_error);
	 	    
	 	
	  }
}



	  
	 	




	
		
		
		
		
	

	

