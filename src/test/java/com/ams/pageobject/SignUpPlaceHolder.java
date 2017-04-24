package com.ams.pageobject;

import org.openqa.selenium.By;
import com.ams.testsetup.SetUp;

public class SignUpPlaceHolder extends SetUp{
	
	//locators to find the element on sign up page
	 By email = By.name("email");
	 By Firstname = By.name("firstname");
	 By Lastname = By.name("lastname");
	 By Designation = By.name("designation");
	 By Role = By.name("role");
	 By Password = By.name("password");
	 By Confirmpwd = By.name("confirm");
	 By signup = By.tagName("span");
	    
    //to verify email placeholder
	public boolean verifyEmailPlaceholder() {
		driver.findElement(signup).click();
		String userPlace = driver.findElement(email).getAttribute("placeholder");
		String placeValue = "enter email";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
	 
	// to verify first name placeholder
	public  boolean verifyFirstNamePlaceholder() {
		driver.findElement(signup).click();
	    String userPlace = driver.findElement(Firstname).getAttribute("placeholder");
		String placeValue = "enter firstname";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
	
	//to verify last name placeholder
	public boolean verifyLastNamePlaceholder() {
	    driver.findElement(signup).click();
	    String userPlace = driver.findElement(Lastname).getAttribute("placeholder");
		String placeValue = "enter lastname";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
	 
	//to verify designation placeholder
	public boolean verifyDesignationPlaceholder() {
	    driver.findElement(signup).click();
	    String userPlace = driver.findElement(Designation).getAttribute("placeholder");
		String placeValue = "enter designation";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
	
	//to verify password placeholder
	public  boolean verifyPasswordPlaceholder() {
	    driver.findElement(signup).click();
	    String userPlace = driver.findElement(Password).getAttribute("placeholder");
		String placeValue = "enter password";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
	 
	//to verify password placeholder
	public boolean verifyConfirmPwdPlaceholder(){
	    driver.findElement(signup).click();
	    String userPlace = driver.findElement(Confirmpwd).getAttribute("placeholder");
		String placeValue = "enter password again";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
	   
	//to verify role field contains value
	public boolean VerifyRoleIsNotEmpty() {
	    driver.findElement(signup).click();
		String rolefield= driver.findElement(Role).getAttribute("value");
		String roleValue = " ROLE_USER";
		if(rolefield.equals(roleValue)) {
			return true;
		}
		return false;
	}	
}