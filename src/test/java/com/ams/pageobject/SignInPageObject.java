package com.ams.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;

public class SignInPageObject extends SetUp {

	public By pageTitle = By.className("panel-title");
	public By emailTextBox = By.name("email");
	public By passwordTextBox = By.name("password");
	public By loginBtn = By.name("loginbutton");
	public By forgotLink = By.linkText("Forgot Password?");
	
	
	public SignInPageObject(WebDriver Driver) {
	    SetUp.driver = Driver;
	}
	
	
	public SignInPageObject() { 
		
	}
	
  
	public boolean verifySignInPageTitle() {
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
	
	
	public boolean verifyLoginButtonIsClicked() {
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
		driver.findElement(By.name("loginBtn")).click();	
		WebElement emailEmpty = driver.findElement(emailTextBox);
			if(emailEmpty.getAttribute("value").isEmpty()){
				return true;
				
			}

		return false;
	}
	/*public boolean verifyLoginFunctionality(){
	 * WebElement email = driver.findElement();
		email.sendKeys("#preet@nerdapplabs.com");
		// Enter Password
		WebElement pwd = driver.findElement();
		pwd.sendKeys("preet123");
		// click on login button
		driver.findElement().click();
		
     }*/
		
		
		
		
		
		
		
	}
	
	


	
	


