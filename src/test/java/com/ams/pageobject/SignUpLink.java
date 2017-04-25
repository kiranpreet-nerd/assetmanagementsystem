package com.ams.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ams.testsetup.SetUp;

public class SignUpLink extends SetUp {

	// Locator to find the element
	By signup = By.tagName("span");
	By register = By.tagName("h3");

	// to verify sign up link is visible
	public boolean verifySignuplinkPresent() {
		if (driver.findElement(signup).isDisplayed()) {
			return true;
		}
		return false;
	}

	// to verify sign up link is enabled
	public boolean verifySignUpLinkEnabled() {
		if (driver.findElement(signup).isEnabled()) {
			return true;
		}
		return false;
	}

	// to verify sign up link is clicked
	public boolean verifySignUpClicked() {
		WebElement signLink = driver.findElement(signup);
		signLink.click();
		String registerText = driver.findElement(register).getText();
		if (registerText.equals("Register")) {
			return true;
		}
		return false;

	}

}