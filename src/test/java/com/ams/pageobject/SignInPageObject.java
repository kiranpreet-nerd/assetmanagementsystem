package com.ams.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;

public class SignInPageObject extends SetUp {
	
    // locators to find the  web elements on web page
	public By pageTitle = By.className("panel-title");
	public static By emailTextBox = By.name("email");
	public static By passwordTextBox = By.name("password");
	public By loginBtn = By.name("loginbutton");
	public By forgotLink = By.linkText("Forgot Password?");
	
	
 	// parameterized constructor to initialize the web driver
	public SignInPageObject(WebDriver Driver) {
	    SetUp.driver = Driver;
	}
	
	// create constructor for initialization
	public SignInPageObject() { 
		
	}
	
	
    //to verify sign page title is present or not
	public boolean verifySignInPageTitle(){
		 WebElement pagetitle = driver.findElement(pageTitle);
		   if(pagetitle.getText().equalsIgnoreCase("Please Sign In")) {
			   return true;    
		   }  
		     return false;   
	  }
	
	// to verify email text box is present or not
	public boolean verifyEmailField() {
		   if (driver.findElement(emailTextBox).isDisplayed()) {	
			   return true;
			}
			return false; 
	  }
	
	// to verify password text box is present or not
	public boolean verifyPasswordField() {
		   if(driver.findElement(passwordTextBox).isDisplayed()){
			  return true;
		   }
		  return false;
	  }
		
	//to verify login button is present or not
	public boolean verifyLoginButtonIsPresent() {
			if(driver.findElement(loginBtn).isDisplayed()) {
				return true;
			}
		  return false;
		 
	    }
	
	//verify login button is enabled or not
	public boolean verifyLoginButtonIsEnabled() {
		   if(driver.findElement(loginBtn).isEnabled()){
			   return true;
		   }
    	  return false;
	}
	
	//to verify login button is clicked or not
	public boolean verifyLoginButtonIsClicked()  {
		  WebElement  LoginBtn = driver.findElement(loginBtn);
		  if(verifyLoginButtonIsEnabled()) {
		    LoginBtn.click();
		    return true;
		  }
		  return false;	  
     }
	
	//verify forgot password link is present or not
	public boolean verifyForgotPasswordLinkIsPresent() {
		if(driver.findElement(forgotLink).isDisplayed()){
			return true;
		}
		return false;
	}
	
	// verify forgot password link is enabled or not
	public boolean verifyForgotPasswordLinkIsEnabled(){
		WebElement forgotlink = driver.findElement(forgotLink);
		if(verifyForgotPasswordLinkIsPresent()){
			forgotlink.isEnabled();
			return true;	
		}
		return false;
	}
	
	// verify forgot password link is clicked or not
	public boolean verifyForgotPasswordLinkIsClickable(){
		WebElement forgotlink = driver.findElement(forgotLink); 
		if(verifyForgotPasswordLinkIsEnabled()){
			forgotlink.click();
			return true;
		}
		return false;
	}
	
	//to verify click on login button, check email text box is empty or not
	public boolean verifyEmailEmpty(){
		 WebElement  login = driver.findElement(loginBtn );
		 login.click();
		WebElement emailEmpty = driver.findElement(emailTextBox);
			if(emailEmpty.getAttribute("value").isEmpty()){
				return true;
				
			}

		return false;
	}
	
	// to verify email placeholder value is matching or not
	public boolean verifyUserPlaceholder(){
		String userPlace = driver.findElement(emailTextBox ).getAttribute("placeholder");
		       String placeValue = "enter email";
		if(userPlace.equals(placeValue)){ 
			return true;
		
		}
		return false;
	}
	
	
	//to verify password placeholder value is matching or not
	public boolean verifyPasswordPlaceholder(){
		  String pwdPlace = driver.findElement(passwordTextBox).getAttribute("placeholder");
		          String placeValue = "enter password";
		          if(pwdPlace.equals(placeValue)){
		        	  return true;
		          }
		          return false;
	}
}
	
		
 
 	

 

 	
 	
 
 	 



		
		
		
		
		
		
		
	
	
	


	
	


