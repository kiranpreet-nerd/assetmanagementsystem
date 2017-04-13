package com.nerdapplabs.test;

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
	List<String[]> dataSource;
	static String username;
	static String password;
	
	  @Test
      public void VerifySignInFunctionality() throws IOException{
			Assert.assertTrue("logged in failed", LoginFunction());
		} 
	
	public boolean LoginFunction() throws IOException {
		 try {
			 dataSource = TestDataReader.readData("Test_Login.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] userNameData = dataSource.get(0);
	 	username = userNameData[0];
		
	 	String[] passwordData = dataSource.get(1);
	 	password = passwordData[0];
	 	
	 	driver.findElement(emailTextBox).sendKeys(username);
	 	driver.findElement(passwordTextBox).sendKeys(password);
	 	WebElement login = driver.findElement( loginBtn);
	 	login.click();
		return true;
	 	}
}



	
		
		
		
		
	

	

