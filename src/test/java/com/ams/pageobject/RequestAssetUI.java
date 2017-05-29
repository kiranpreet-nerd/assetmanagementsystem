package com.ams.pageobject;

import org.openqa.selenium.By;

import com.ams.testsetup.LoginPage;
import com.ams.testsetup.SetUp;
import com.ams.testsetup.SetUpLogin;

public class RequestAssetUI extends SetUp{

	static By emailReq = By.name("email");
	static By logout = By.linkText("LOGOUT");
	static By type = By.name("assettype");
	static By name = By.name("assetname");
	static By quant = By.name("quantity");
	static By reasonTxt = By.name("reason");
	static By requestDate = By.name("requestdate");
	static By requestBtn = By.name("requestbutton");
	
	// to verify email field is visible on request asset page
	public static boolean verifyEmailFiedOnReqAssetVisible() {
		 LoginPage.login_User();
		if (driver.findElement(emailReq).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify "asset type " field is visible
	public boolean verifyAssetTypeFieldPresent() {
		 LoginPage.login_User();
		if (driver.findElement(type).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify "asset Name" field is displayed
	public boolean verifyAssetNameFieldPresent() {
		 LoginPage.login_User();
		if (driver.findElement(name).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify "number of asset needed" field is visible
	public boolean verifyNumberOfAssetNeededFieldPresent() {
		 LoginPage.login_User();
		if (driver.findElement(quant).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify reason text-area is visible
	public boolean verifyReasonTxtAreaFieldPresent() {
		 LoginPage.login_User();
		if (driver.findElement(reasonTxt).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify request date field is visible or not
	public boolean verifyRequestDateFieldPresent() {
		 LoginPage.login_User();
		if (driver.findElement(requestDate).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify request button is displayed or not
	public boolean verifyRequesBtnFieldVisible() {
		 LoginPage.login_User();
		if (driver.findElement(requestBtn).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify request button is enabled or not
	public boolean verifyRequesBtnFieldEnabled() {
		 LoginPage.login_User();
		if (driver.findElement(requestBtn).isEnabled()) {
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