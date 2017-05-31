package com.ams.testsetup;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.nerdapplabs.test.TestDataReader;

public class LoginPage extends SetUp {

	public static By emailTextBox = By.name("email");
	public static By passwordTextBox = By.name("password");
	public static By loginBtn = By.name("loginbutton");

	static List<String[]> dataSource;
	static String username;
	static String password;

	public static void login_User() {

		try {
			dataSource = TestDataReader.readData("Test_Login.csv");
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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}