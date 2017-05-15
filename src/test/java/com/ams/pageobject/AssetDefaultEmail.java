package com.ams.pageobject;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import com.ams.testsetup.SetUpLogin;
import com.nerdapplabs.test.TestDataReader;

public class AssetDefaultEmail extends SetUpLogin {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By emailReq = By.name("email");
	static By logout = By.linkText("LOGOUT");

	 List<String[]> dataSource;
	 String username;
	
	
	// verify email field is visible on request
	// asset page
	public  boolean verifyEmailFieldContainsRegisteredEmail() throws IOException {
		try {
			dataSource = TestDataReader.readData("Test_Login.csv");
		} catch (IOException e) {

			e.printStackTrace();
		}
		String[] userNameData = dataSource.get(0);
		username = userNameData[0];
		String emailValue = driver.findElement(emailReq).getAttribute("value");
		if (emailValue.equals(username)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void tearDown() {
		driver.findElement(logout).click();
		super.tearDown();
		
	}
}