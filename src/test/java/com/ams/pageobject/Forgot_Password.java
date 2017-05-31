package com.ams.pageobject;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;
import com.ams.test.TestDataReader;

public class Forgot_Password extends SetUp {

	By forgotPwd = By.linkText("Forgot Password");
	By email = By.name("email");
	By forgotBtn = By.name("forgotpasswordbutton");
	By error_msg = By.cssSelector("div[class ='redalert']");
	By verify_msg = By.className("redalert");

	List<String[]> dataSource;
	static String username;

	// to verify forgot link is clicked
	public boolean verifyForgotLinkClickable() {
		driver.findElement(forgotPwd).click();
		if (driver.findElement(forgotBtn).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify email text box is present on forgot password page
	public boolean verifyEmailField() {
		driver.findElement(forgotPwd).click();
		if (driver.findElement(email).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify validation on empty email field of forgot password page
	public boolean verifyEmailEmptyFieldValidation() {
		driver.findElement(forgotPwd).click();
		driver.findElement(forgotBtn).click();
		String emailValue = driver.findElement(email).getAttribute("value");
		if (emailValue.isEmpty() && driver.findElement(error_msg).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify get password button is displayed and enabled
	public boolean verifyGetPwdBtnVisibleAndEnabled() {
		driver.findElement(forgotPwd).click();
		if (driver.findElement(forgotBtn).isDisplayed() && driver.findElement(forgotBtn).isEnabled()) {
			return true;
		}
		return false;
	}

	// to verify email placeholder
	public boolean verifyEmailPlaceholder() {
		if (driver.findElement(email).getAttribute("placeholder").equals("enter email")) {
			return true;
		}
		return false;
	}

	// to verify forgot password functionality
	public boolean verifyForgotPwdFunctionlity() {
		try {
			dataSource = TestDataReader.readData("Test_Login.csv");
		} catch (IOException e) {

			e.printStackTrace();
		}
		driver.findElement(forgotPwd).click();
		String[] userNameData = dataSource.get(0);
		username = userNameData[0];
		// find email text box and send email
		driver.findElement(email).sendKeys(username);
		driver.findElement(forgotBtn).click();
		if (driver.findElement(verify_msg).isDisplayed()) {
			return true;
		}
		return false;
	}

}