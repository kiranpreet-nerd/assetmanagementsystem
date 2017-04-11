package com.nerdapplabs.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ams.pageobject.SignInPageObject;
import com.ams.testsetup.SetUp;
import au.com.bytecode.opencsv.CSVReader;
	 
public class csvPageObject extends SignInPageObject {

	
	public csvPageObject(WebDriver driver) {
		 SetUp.driver = driver;
	}
	
	public csvPageObject() {
		
	}
	csvPageObject csvpageobject = new csvPageObject();
	List<String[]> dataSource = csvpageobject.readCsv("testData.csv");
		
	
	public void verifyLoginFunctionality() {
			// find element by name and enter invalid email
			WebElement email = driver.findElement(emailTextBox );
			email.sendKeys();
			// find element by name and enter invalid password
			WebElement pwd = driver.findElement(passwordTextBox);
			pwd.sendKeys();
			// click on login button
			driver.findElement(loginBtn).click();
	}
   


	
		
		
		
		
	

	

