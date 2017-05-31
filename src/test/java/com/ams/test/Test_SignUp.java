package com.ams.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ams.testsetup.SetUp;

public class Test_SignUp extends SetUp {

	// locators to find the element on web page
	By email = By.name("email");
	By Firstname = By.name("firstname");
	By Lastname = By.name("lastname");
	By Designation = By.name("designation");
	By Role = By.name("role");
	By Password = By.name("password");
	By Confirmpwd = By.name("confirm");
	By signup = By.tagName("span");
	By signbtn = By.name("registerbutton");

	// to verify sign up functionality pass or not
	@Test
	public void verifySignUpFunctionality() {
		try {
			Assert.assertTrue("failed to logged in", SignUpFunction());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	List<String[]> dataSource;
	String useremail;
	String firstname;
	String lastname;
	String designation;
	String password;
	String confirmPwd;

	public boolean SignUpFunction() throws IOException {
		try {
			dataSource = TestDataReader.readData("Test_SignUp.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] userEmailData = dataSource.get(0);
		useremail = userEmailData[0];

		String[] FirstNameData = dataSource.get(1);
		firstname = FirstNameData[0];

		String[] LastNameData = dataSource.get(2);
		lastname = LastNameData[0];

		String[] DesignationData = dataSource.get(3);
		designation = DesignationData[0];

		String[] passwordData = dataSource.get(4);
		password = passwordData[0];

		String[] confirmPasswordData = dataSource.get(5);
		confirmPwd = confirmPasswordData[0];

		// find sign up link
		WebElement signUpClick = driver.findElement(signup);
		// click on sign up link
		signUpClick.click();
		// find email text box and send email
		driver.findElement(email).sendKeys(useremail);
		// find first name text box and send name
		driver.findElement(Firstname).sendKeys(firstname);
		// find last name field and send data
		driver.findElement(Lastname).sendKeys(lastname);
		// find designation field and send data
		driver.findElement(Designation).sendKeys(designation);
		// find password field and send password
		driver.findElement(Password).sendKeys(password);
		// find confirm password field and send data
		driver.findElement(Confirmpwd).sendKeys(confirmPwd);
		// find register button and click on it
		driver.findElement(signbtn).click();
		// find sign up link
		WebElement signLink = driver.findElement(signup);
		if (signLink.isDisplayed()) {
			return true;
		}
		return false;

	}
}
