package com.nerdapplabs.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.SignInPageObject;

public class SignInTest extends SignInPageObject {
	
// create the object of signInPageObject to access the methods 		
 SignInPageObject signInPage = new SignInPageObject(driver);
  
 //create constructor for initialization
 public SignInTest()
 {
	 
 }

       @Test
   	    public void verifyLoginInPageTitle() { 
    	  
    	 Assert.assertTrue("Login Page title is not displayed", signInPage.verifySignInPageTitle() );
        }
       
       @Test
       public void verifyEmailFieldIsPresent() {
       Assert.assertTrue(signInPage.verifyEmailField());
       }
       
       @Test
        public void verifyPasswordTextFieldIsDisplayed(){
    	Assert.assertTrue(signInPage.verifyPasswordField());
       }
       
       @Test
       public void verifyEmailPlaceholder() {
    	 Assert.assertTrue("email placeholder is not present",signInPage.verifyUserPlaceholder());  
       }
       
       @Test
       public void verifyPwdPlcaeHolder(){
    	   Assert.assertTrue("password placeholder is not present",signInPage.verifyPasswordPlaceholder() );
       }
       @Test
        public void verifyLoginButtonIsDisplayed() {
    	   Assert.assertTrue("Login button is not displayed", signInPage.verifyLoginButtonIsPresent() );
       }
       
       @Test 
        public void verifySignInButtonIsEnabled(){
    	   Assert.assertTrue("Sign button is not enabled", signInPage.verifyLoginButtonIsEnabled()  );    
       }
       
       @Test
       public void verifySignInButtonIsClickable(){
    	   Assert.assertTrue("Sign button is not clickable", signInPage.verifyLoginButtonIsClicked()  );    
       }
	  
       @Test
       public void verifyForgotPasswordLinkIsDisplayed(){
    	   Assert.assertTrue("Forgot password link is invisible", signInPage.verifyForgotPasswordLinkIsPresent());
       }
       
       @Test
       public void verifyForgotLinkIsClicked(){
    	   Assert.assertTrue("Forgot password link is not clickable", signInPage.verifyForgotPasswordLinkIsClickable());  
    	   
       }
       @Test
       public void verifyEmailTextBoxIsEmpty() throws IOException{
    	   Assert.assertTrue("Email field is not empty", signInPage.verifyEmailEmpty());  
       }
     
      
}
		 
       


