package com.ams.pageobject;

import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;

public class SignUpFieldsVisible extends SetUp {
	
	//locators to find the element on web page
	 By email = By.name("email");
     By Firstname = By.name("firstname");
     By Lastname = By.name("lastname");
     By Designation = By.name("designation");
     By Role = By.name("role");
     By Password = By.name("password");
     By Confirmpwd = By.name("confirm");
     By signup = By.tagName("span");
    
    //verify email text box is present
    public boolean verifyEmailTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(email).isDisplayed()){
			return true;
		}
		return false;
    }
    
    //verify first name text box is present
    public boolean verifyFirstNameTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(Firstname).isDisplayed()){
			return true;
		}
		return false;
    }
    
    //verify last name text box is present
    public boolean verifyLastNameTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(Lastname).isDisplayed()){
			return true;
		}
		return false;
    }
 
    //Verify designation text box is present
    public boolean verifyDesignationTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(Designation).isDisplayed()){
			return true;
		}
		return false;
    }
 
    //verify role text box is present
    public boolean verifyRoleTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(Role).isDisplayed()){
			return true;
		}
		return false;
    }
 
    //verify password text Box is present
    public boolean verifyPasswordTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(Password).isDisplayed()){
			return true;
		}
		return false;
    }
 
    //verify confirm password text box is present
    public boolean verifyConfirmPwdTextBox() {
    	driver.findElement(signup).click();
		if(driver.findElement(Confirmpwd).isDisplayed()){
			return true;
		}
		return false;
    }
}
