package com.ams.pageobject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ams.testsetup.SetUp;
import com.nerdapplabs.test.TestDataReader;

public class Test_RequestAssetUI extends SetUp {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By emailReq = By.name("email");
	static By logout = By.linkText("LOGOUT");
	static By type = By.name("assettype");
	static By name = By.name("assetname");
	static By quant = By.name("quantity");
	static By reasonTxt = By.name("reason");
	static By requestDate = By.name("requestdate");
	static By requestBtn = By.name("requestbutton");

	static List<String[]> dataSource;
	static String username;
	static String password;
	static String userName;

	// to login the application
	public static boolean loginFunction() throws IOException {
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
		String lgout = driver.findElement(logout).getText();
		String logged = "LOGOUT";
		if (lgout.equals(logged)) {
			return true;
		}
		return false;
	}

	// to verify email field is visible on request asset page
	public static boolean verifyEmailFiedOnReqAssetVisible() {
		try {
			if (loginFunction()) {
				if (driver.findElement(emailReq).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	// to verify "asset type " field is visible
	public boolean verifyAssetTypeFieldPresent() {
		try {
			if (loginFunction()) {
				if (driver.findElement(type).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify "asset Name" field is displayed
	public boolean verifyAssetNameFieldPresent() {
		try {
			if (loginFunction()) {
				if (driver.findElement(name).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify "number of asset needed" field is visible
	public boolean verifyNumberOfAssetNeededFieldPresent() {
		try {
			if (loginFunction()) {
				if (driver.findElement(quant).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify reason text-area is visible
	public boolean verifyReasonTxtAreaFieldPresent() {
		try {
			if (loginFunction()) {
				if (driver.findElement(reasonTxt).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify request date field is visible or not
	public boolean verifyRequestDateFieldPresent() {
		try {
			if (loginFunction()) {
				if (driver.findElement(requestDate).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify request button is displayed or not
	public boolean verifyRequesBtnFieldVisible() {
		try {
			if (loginFunction()) {
				if (driver.findElement(requestBtn).isDisplayed())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify request button is enabled or not
	public boolean verifyRequesBtnFieldEnabled() {
		try {
			if (loginFunction()) {
				if (driver.findElement(requestBtn).isEnabled())
					return true;
				else
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}