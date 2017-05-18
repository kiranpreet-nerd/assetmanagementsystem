package com.ams.pageobject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;

public class SignUp_Validations extends SetUp {

	By signup = By.tagName("span");
	By signbtn = By.name("registerbutton");
	By email = By.name("email");
	By firstName = By.name("firstname");
	By lastName = By.name("lastname");
	By designation = By.name("designation");
	By role = By.name("role");
	By passwrd = By.name("password");
	By confirmPwd = By.name("confirm");

	// to verify alert message on empty email
	public boolean verifyEmailValidate() {
		driver.findElement(signup).click();
		driver.findElement(signbtn).click();
		try {
		// to locate the alert using switch statement
		Alert al = driver.switchTo().alert();

		// to get text from alert and store into string variable
		String alertText = al.getText();

		// to check actual text equals to expected text
		if (alertText.equals("email required"))
			// In oder to handle the alert message click on OK button
			al.accept();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		String emailtext = driver.findElement(email).getAttribute("value");
		if(emailtext.isEmpty()){
			return true;
		}
		return false;
		
	}

	// to verify alert message on empty first name
	public boolean verifyFirstNameValidate() {
		driver.findElement(signup).click();
		driver.findElement(email).sendKeys("har@");
		driver.findElement(signbtn).click();
		try {
			// to locate the alert using switch statement
			Alert al = driver.switchTo().alert();

			// to get text from alert and store into string variable
			String alertText = al.getText();

			// to check actual text equals to expected text
			if (alertText.equals("first name required"))
				// In oder to handle the alert message click on OK button
				al.accept();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		String firstname = driver.findElement(firstName).getText();
		if (firstname.isEmpty()) {
			return true;
		}
		return false;
			
	}

	// to verify alert message on empty last name
	public boolean verifyLastNameValidate() {
		driver.findElement(signup).click();
		driver.findElement(email).sendKeys("har@");
		driver.findElement( firstName).sendKeys("preet");
		driver.findElement(signbtn).click();
		try {
			// to locate the alert using switch statement
			Alert al = driver.switchTo().alert();

			// to get text from alert and store into string variable
			String alertText = al.getText();

			// to check actual text equals to expected text
			if (alertText.equals("last name required"))
				// In oder to handle the alert message click on OK button
				al.accept();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		String lastname = driver.findElement(lastName).getText();
		if (lastname.isEmpty()) {
				return true;
		}
		return false;
	}

	// to validate alert message on empty designation
	public boolean verifyDesignationValidate() {
		driver.findElement(signup).click();
		driver.findElement(email).sendKeys("har@");
		driver.findElement( firstName).sendKeys("har");
		driver.findElement(lastName).sendKeys("kaur");
		driver.findElement(signbtn).click();
		try {
			// to locate the alert using switch statement
			Alert al = driver.switchTo().alert();

			// to get text from alert and store into string variable
			String alertText = al.getText();

			// to check actual text equals to expected text
			if (alertText.equals("designation required"))
				// In oder to handle the alert message click on OK button
				al.accept();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		String designationtxt = driver.findElement(designation).getText();
		if (designationtxt.isEmpty()) {
				return true;
		}
			return false;
		}
	

	// to validate alert message on empty password
	public boolean verifyPasswordValidate() {
		driver.findElement(signup).click();
		driver.findElement(email).sendKeys("har@");
		driver.findElement( firstName).sendKeys("har");
		driver.findElement(lastName).sendKeys("kaur");
		driver.findElement(designation).sendKeys("test");
		driver.findElement(signbtn).click();
		try {
			// to locate the alert using switch statement
			Alert al = driver.switchTo().alert();

			// to get text from alert and store into string variable
			String alertText = al.getText();

			// to check actual text equals to expected text
			if (alertText.equals("password required"))
				// In oder to handle the alert message click on OK button
				al.accept();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		String passwordtxt = driver.findElement(passwrd).getText();
		if (passwordtxt.isEmpty()) {
			return true;
		}
			return false;
		}
	

	// to validate alert message on empty confirm password
	public boolean verifyConfirmPwdValidate() {
		driver.findElement(signup).click();
		driver.findElement(email).sendKeys("har@");
		driver.findElement( firstName).sendKeys("har");
		driver.findElement(lastName).sendKeys("kaur");
		driver.findElement(designation).sendKeys("test");
		driver.findElement(passwrd).sendKeys("preet");
		driver.findElement(signbtn).click();
		try {
			// to locate the alert using switch statement
			Alert al = driver.switchTo().alert();

			// to get text from alert and store into string variable
			String alertText = al.getText();

			// to check actual text equals to expected text
			if (alertText.equals("confirm password required"))
				// In oder to handle the alert message click on OK button
				al.accept();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		String confirmtxt = driver.findElement(confirmPwd).getText();
		if (confirmtxt.isEmpty()) {
		    	return true;
		}
			return false;
		}
	}