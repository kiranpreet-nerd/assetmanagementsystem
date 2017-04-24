package com.ams.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ams.testsetup.SetUp;

public class SignUpFieldsEmpty extends SetUp {
	
	//locators to find the element on web page
	 By email = By.name("email");
	 By Firstname = By.name("firstname");
	 By Lastname = By.name("lastname");
	 By Designation = By.name("designation");
	 By Role = By.name("role");
	 By Password = By.name("password");
	 By Confirmpwd = By.name("confirm");
     By signup = By.tagName("span");
	
	//to verify email field is empty
	public  boolean VerifyEmailEmpty() {
		driver.findElement(signup).click();
		WebElement emailEmpty = driver.findElement(email);
		if(emailEmpty.getAttribute("value").isEmpty()){
			return true;
		}
		return false;
	}
	
	//verify first name field is empty	    
	public boolean VerifyFirstNameEmpty() {
		driver.findElement(signup).click();
		WebElement firstnameEmpty = driver.findElement(Firstname);
			if(firstnameEmpty.getAttribute("value").isEmpty()){
				return true;
			}
			return false;
		}
	
	//to verify last name field is empty
	public boolean VerifyLastNameEmpty() {
		driver.findElement(signup).click();
		WebElement LastnameEmpty = driver.findElement(Firstname);
			if(LastnameEmpty.getAttribute("value").isEmpty()){
				return true;
			}
			return false;
		}
		
	//to verify designation field is empty
	public boolean VerifyDesignationEmpty() {
		driver.findElement(signup).click();
		WebElement DesignationEmpty = driver.findElement(Firstname);
			if(DesignationEmpty.getAttribute("value").isEmpty()){
				return true;
			}
			return false;
		}
		   
	//to verify password field is empty
	public boolean VerifyPasswordEmpty() {
		driver.findElement(signup).click();
		WebElement passwordEmpty = driver.findElement(Firstname);
		if(passwordEmpty.getAttribute("value").isEmpty()){
			return true;
		  }
		   return false;
		}
	
	//to verify confirm password field is empty
	public boolean VerifyConfirmpwdEmpty() {
		driver.findElement(signup).click();
		WebElement confirpwdEmpty = driver.findElement(Firstname);
		if(confirpwdEmpty.getAttribute("value").isEmpty()){
			return true;
		}
		return false;
	}		
}