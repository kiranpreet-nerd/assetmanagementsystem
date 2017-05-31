package com.ams.test;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.ams.testsetup.SetUp;

public class Test_Logout extends SetUp {

	// locator to find web elements
	By emailTextBox = By.name("email");
	By passwordTextBox = By.name("password");
	By loginBtn = By.name("loginbutton");
	By logout = By.linkText("LOGOUT");
	By signup = By.linkText("SIGN UP");

	List<String[]> dataSource;
	static String username;
	static String password;

	// to check logout Functionality test pass or not
	@Test
	public void logoutFunctionality() throws IOException {
		Assert.assertTrue(logoutFunction(), "LOGOUT FAILED");
	}

	// To verify logout functionality works
	public boolean logoutFunction() throws IOException {
		try {
			dataSource = TestDataReader.readData("Test_Logout.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] userNameData = dataSource.get(0);
		username = userNameData[0];
		String[] passwordData = dataSource.get(1);
		password = passwordData[0];
		// find email text box and send email
		driver.findElement(emailTextBox).sendKeys(username);
		// find password text box and send password
		driver.findElement(passwordTextBox).sendKeys(password);
		// find login button and click on it
		driver.findElement(loginBtn).click();
		// find logout button and click on it
		driver.findElement(logout).click();
		// find sign up link
		WebElement SignUp = driver.findElement(signup);
		if (SignUp.isDisplayed()) {
			return true;
		}
		return false;
	}
}