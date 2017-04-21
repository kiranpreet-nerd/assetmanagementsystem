package com.nerdapplabs.test;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;

public class Test_Login extends SetUp {
	// to find web elements from web page
	public static By emailTextBox = By.name("email");
	public static By passwordTextBox = By.name("password");
	public By loginBtn = By.name("loginbutton");
	public By errorText = By.className(".login-panel.panel.panel-default"); 
	public static By logout = By.linkText("LOGOUT");

	List<String[]> dataSource;
	static String username;
	static String password;
	
	  @Test
	  public void verifyLoginFunctionality() {
		  try {
			Assert.assertTrue("failed to logged in", LoginFunction());
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  
	  /* @ this method is to verify login functionality with multiple test data through csv file
	   * exception IOException on input error
	   * @param username to get value of email from string array
	   * @param password to get value of password from string array
	   * @array dataSource to read test data from csv file 
	   * @array userNameData to get values from dataSource 
		 */
	
        public boolean LoginFunction() throws IOException {
		 try {
			 dataSource = TestDataReader.readData("Test_Login.csv");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String[] userNameData = dataSource.get(0);
	 	username = userNameData[0];
	 	String[] passwordData = dataSource.get(1);
	 	password = passwordData[0];
	 	//find email text box and send email
	 	driver.findElement(emailTextBox).sendKeys(username);
	 	//find password text box and send password
	 	driver.findElement(passwordTextBox).sendKeys(password);
	 	//find login button 
	 	WebElement login = driver.findElement( loginBtn);
	 	//click on login button
	 	login.click();
	 	String lgout = driver.findElement(logout).getText();
	 	String logged = "LOGOUT";
	 	if(lgout.equals(logged)) {
	 		return true;
	 	}
	 	return false;
	 	
	  }
}