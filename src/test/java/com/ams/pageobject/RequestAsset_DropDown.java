package com.ams.pageobject;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ams.testsetup.SetUp;
import com.nerdapplabs.test.TestDataReader;

public class RequestAsset_DropDown extends SetUp {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By logout = By.linkText("LOGOUT");
	static By type = By.id("ddl");
	static By name = By.name("assetname");

	static List<String[]> dataSource;
	static String username;
	static String password;
	String listvalue;

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

	// to verify "asset Type" drop-down is single select or multiple select
	public boolean verifyDropDownIsSingleSelect() {
		try {
			loginFunction();
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			if (!(drop.isMultiple())) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// verify "asset type" drop-down options count is 4
	public boolean verifyAssetTypeDropDownCount() {
		try {
			loginFunction();
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			List<WebElement> options = drop.getOptions();
			int count = options.size();
			if (count == 4) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to verify default value in "asset Type" drop down

	public boolean verifyDefaultValueOfTypeDropDown() {
		try {
			loginFunction();
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			List<WebElement> options = drop.getOptions();
			int count = options.size();
			for (int i = 0; i < count; i++) {
				listvalue = options.get(i).getText();
				if (listvalue.equals("None")) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// verify to select the option from "asset type" may impact on another
	// drop-down
	public boolean verifyImpactOnNameDropDown() {
		try {
			loginFunction();
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			drop.selectByVisibleText("Asset");
			String nameDrop = driver.findElement(name).getAttribute("value");
			if (!(nameDrop.isEmpty())) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	// verify "None" value of "asset type" does't impact on assetName drop-down
	public boolean verifyNoneOptionImpactOnNameDropDown() {
		try {
			loginFunction();
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			drop.selectByVisibleText("None");
			String nameDrop = driver.findElement(name).getAttribute("value");
			if (nameDrop.isEmpty()) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}