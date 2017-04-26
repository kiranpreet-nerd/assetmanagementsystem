package com.ams.pageobject;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ams.testsetup.SetUp;
import com.nerdapplabs.test.TestDataReader;

public class Test_AssetDefaultEmail extends SetUp {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By emailReq = By.name("email");

	static List<String[]> dataSource;
	static String username;
	static String password;

	@Test
	public void verifyEmailFieldFilledWithRegisteredEmail() throws IOException {
		Assert.assertTrue("Email field not contains default registered email",
				verifyEmailFieldContainsRegisteredEmail());
	}

	// to login the application and verify email field is visible on request
	// asset page
	public static boolean verifyEmailFieldContainsRegisteredEmail() throws IOException {
		try {
			dataSource = TestDataReader.readData("Test_Login.csv");
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
		// find login button
		WebElement login = driver.findElement(loginBtn);
		// click on login button
		login.click();
		String emailValue = driver.findElement(emailReq).getAttribute("value");
		if (emailValue.equals(username)) {
			return true;
		}
		return false;
	}
}