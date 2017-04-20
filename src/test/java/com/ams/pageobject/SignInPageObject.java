package com.ams.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;

public class SignInPageObject extends SetUp {
	
//locators to find the  web elements on web page
public static By pageTitle = By.className("panel-title");
public static By emailTextBox = By.name("email");
public static By passwordTextBox = By.name("password");
public static By loginBtn = By.name("loginbutton");
public static By forgotLink = By.linkText("Forgot Password?");

	// create constructor for initialization
	public SignInPageObject() { 
	}
	
	//to verify sign page title is present or not
	public static boolean verifySignInPageTitle(){
		WebElement pagetitle = driver.findElement(pageTitle);
		if(pagetitle.getText().equalsIgnoreCase("Please Sign In")) {
			return true;    
		}  
		return false;   
	}
	
	// to verify email text box is present or not
	public static boolean verifyEmailField() {
		if (driver.findElement(emailTextBox).isDisplayed()) {	
			return true;
		}
		return false; 
	}
	
	// to verify password text box is present or not
	public static boolean verifyPasswordField() {
		if(driver.findElement(passwordTextBox).isDisplayed()){
			return true;
		}
		return false;
	}
		
	//to verify login button is present or not
	public static boolean verifyLoginButtonIsPresent() {
		if(driver.findElement(loginBtn).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	//verify login button is enabled or not
	public static boolean verifyLoginButtonIsEnabled() {
		if(driver.findElement(loginBtn).isEnabled()){
			return true;
		}
		return false;
	}
	
	//to verify login button is clicked or not
	public static boolean verifyLoginButtonIsClicked()  {
		WebElement  LoginBtn = driver.findElement(loginBtn);
		if(verifyLoginButtonIsEnabled()) {
			LoginBtn.click();
			return true;
		}
		return false;	  
	}
	
	//verify forgot password link is present or not
	public static boolean verifyForgotPasswordLinkIsPresent() {
		if(driver.findElement(forgotLink).isDisplayed()){
			return true;
		}
		return false;
	}
	
	// verify forgot password link is enabled or not
	public static boolean verifyForgotPasswordLinkIsEnabled(){
		WebElement forgotlink = driver.findElement(forgotLink);
		if(verifyForgotPasswordLinkIsPresent()){
			forgotlink.isEnabled();
			return true;	
		}
		return false;
	}
	
	// verify forgot password link is clicked or not
	public static boolean verifyForgotPasswordLinkIsClickable(){
		WebElement forgotlink = driver.findElement(forgotLink); 
		if(verifyForgotPasswordLinkIsEnabled()){
			forgotlink.click();
			return true;
		}
		return false;
	}
	
	//to verify click on login button, check email text box is empty or not
	public static boolean verifyEmailEmpty(){
		WebElement emailEmpty = driver.findElement(emailTextBox);
		if(emailEmpty.getAttribute("value").isEmpty()){
			return true;
		}
		return false;
	}
	
	// to verify email placeholder value is matching or not
	public static boolean verifyUserPlaceholder(){
		String userPlace = driver.findElement(emailTextBox ).getAttribute("placeholder");
		String placeValue = "enter email";
		if(userPlace.equals(placeValue)){ 
			return true;
		}
		return false;
	}
		
	//to verify password placeholder value is matching or not
	public static boolean verifyPasswordPlaceholder(){
		String pwdPlace = driver.findElement(passwordTextBox).getAttribute("placeholder");
		String placeValue = "enter password";
		if(pwdPlace.equals(placeValue)){
			return true;
		}
		return false;
	}	
}