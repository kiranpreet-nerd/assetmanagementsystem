package com.ams.pageobject;

import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;

public class SignUp_Validations extends SetUp {

	By signup = By.tagName("span");
	By signbtn = By.name("registerbutton");
	By email_error = By.id("email.errors");
	By firstname_error = By.id("firstname.errors");
	By lastname_error = By.id("lastname.errors");
	By designation_error = By.id("designation.errors");
	By password_error = By.id("password.errors");
	By confirmpwd_error = By.id("confirm.errors");
	By email = By.name("email");
	By firstName = By.name("firstname");
	By lastName = By.name("lastname");
	By designation = By.name("designation");
	By role = By.name("role");
	By passwrd = By.name("password");
	By confirmPwd = By.name("confirm");

	// to verify error message on empty email
	public boolean verifyEmailValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String emailtext = driver.findElement(email).getText();
		if (emailtext.isEmpty()) {
			if (driver.findElement(email_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	// to verify error message on empty first name
	public boolean verifyFirstNameValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String firstname = driver.findElement(firstName).getText();
		if (firstname.isEmpty()) {
			if (driver.findElement(firstname_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	// to verify error message on empty last name
	public boolean verifyLastNameValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String lastname = driver.findElement(lastName).getText();
		if (lastname.isEmpty()) {
			if (driver.findElement(lastname_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	// to validate error message on empty designation
	public boolean verifyDesignationValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String designationtxt = driver.findElement(designation).getText();
		if (designationtxt.isEmpty()) {
			if (driver.findElement(designation_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	// to validate error message on empty password
	public boolean verifyPasswordValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String passwordtxt = driver.findElement(passwrd).getText();
		if (passwordtxt.isEmpty()) {
			if (driver.findElement(password_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}

	// to validate error message on empty confirm password
	public boolean verifyConfirmPwdValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String confirmtxt = driver.findElement(confirmPwd).getText();
		if (confirmtxt.isEmpty()) {
			if (driver.findElement(confirmpwd_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
}