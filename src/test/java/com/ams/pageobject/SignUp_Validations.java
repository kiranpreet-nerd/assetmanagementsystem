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
	By Firstname = By.name("firstname");
	By Lastname = By.name("lastname");
	By Designation = By.name("designation");
	By Role = By.name("role");
	By Password = By.name("password");
	By Confirmpwd = By.name("confirm");

	// to verify error message on empty email
	public boolean verifyEmailValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		String EmailText = driver.findElement(email).getText();
		if (EmailText.isEmpty()) {
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
		String firstName = driver.findElement(Firstname).getText();
		if (firstName.isEmpty()) {
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
		String lastName = driver.findElement(Lastname).getText();
		if (lastName.isEmpty()) {
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
		String designationTxt = driver.findElement(Designation).getText();
		if (designationTxt.isEmpty()) {
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
		String passwordTxt = driver.findElement(Password).getText();
		if (passwordTxt.isEmpty()) {
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
		String confirmTxt = driver.findElement(Confirmpwd).getText();
		if (confirmTxt.isEmpty()) {
			if (driver.findElement(confirmpwd_error).isDisplayed())
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
}