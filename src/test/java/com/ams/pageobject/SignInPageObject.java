package com.ams.pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;
import com.nerdapplabs.test.TestDataReader;

public class SignInPageObject extends SetUp {

	public By pageTitle = By.className("panel-title");
	public static By emailTextBox = By.name("email");
	public static By passwordTextBox = By.name("password");
	public By loginBtn = By.name("loginbutton");
	public By forgotLink = By.linkText("Forgot Password?");
	
	String username;
 	String password;
 	String validemail;
 	String validpwd;
 	String[] cellNum = null;
 	
	public SignInPageObject(WebDriver Driver) {
	    SetUp.driver = Driver;
	}
	
	
	public SignInPageObject() { 
		
	}
	
	
  
	public boolean verifySignInPageTitle(){
		 WebElement pagetitle = driver.findElement(pageTitle);
		   if(pagetitle.getText().equalsIgnoreCase("Please Sign In")) {
			   return true;    
		   }  
		     return false;   
	  }
	
	
	public boolean verifyEmailField() {
		   if (driver.findElement(emailTextBox).isDisplayed()) {	
			   return true;
			}
			return false; 
	  }
	
	
	public boolean verifyPasswordField() {
		   if(driver.findElement(passwordTextBox).isDisplayed()){
			  return true;
		   }
		  return false;
	  }
		
	
	public boolean verifyLoginButtonIsPresent() {
			if(driver.findElement(loginBtn).isDisplayed()) {
				return true;
			}
		  return false;
		 
	    }
	
	
	public boolean verifyLoginButtonIsEnabled() {
		   if(driver.findElement(loginBtn).isEnabled()){
			   return true;
		   }
    	  return false;
	}
	
	
	public boolean verifyLoginButtonIsClicked()  {
		  WebElement  LoginBtn = driver.findElement(loginBtn);
		  if(verifyLoginButtonIsEnabled()) {
		    LoginBtn.click();
		    return true;
		  }
		  return false;	  
     }
	
	
	public boolean verifyForgotPasswordLinkIsPresent() {
		if(driver.findElement(forgotLink).isDisplayed()){
			return true;
		}
		return false;
	}
	
		
	public boolean verifyForgotPasswordLinkIsEnabled(){
		WebElement forgotlink = driver.findElement(forgotLink);
		if(verifyForgotPasswordLinkIsPresent()){
			forgotlink.isEnabled();
			return true;	
		}
		return false;
	}
	
	
	public boolean verifyForgotPasswordLinkIsClickable(){
		WebElement forgotlink = driver.findElement(forgotLink); 
		if(verifyForgotPasswordLinkIsEnabled()){
			forgotlink.click();
			return true;
		}
		return false;
	}
	
	public boolean verifyEmailEmpty(){
		 WebElement  login = driver.findElement(loginBtn );
		 login.click();
		WebElement emailEmpty = driver.findElement(emailTextBox);
			if(emailEmpty.getAttribute("value").isEmpty()){
				return true;
				
			}

		return false;
	}
 
 	
 	

 /*public boolean verifyValidEmailandPwd() throws IOException{
	
 	validemail = TestDataReader.readData(3);
 	validpwd = TestDataReader.readData(4);
	driver.findElement( emailTextBox).sendKeys(validemail);
 	driver.findElement(passwordTextBox).sendKeys(validpwd);
 	WebElement logn= driver.findElement( loginBtn);
 	logn.click();
 	
	return true;
	
 	}*/
 	
}
 	
 	
 
 	 



		
		
		
		
		
		
		
	
	
	


	
	


