package com.nerdapplabs.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public boolean verifyLoginWithMultipleTestData() {
		CSVReader csvReader;
		try {
			String filePath = null;
			csvReader = new CSVReader(new FileReader(filePath));
			String[] cell = null;
			try {
				while((cell= csvReader.readNext()) != null) {
				       String passData = cell[0];
				    // find element by name and enter invalid email
						WebElement email = driver.findElement(emailTextBox );
						email.sendKeys(passData);
						// find element by name and enter invalid password
						WebElement pwd = driver.findElement(passwordTextBox);
						pwd.sendKeys(passData);
						// click on login button
						driver.findElement(loginBtn).click();
						
				}
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
		
		} 
	   catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} 
	
	return true;
	}
}


	
		
		
		
		
	

	

