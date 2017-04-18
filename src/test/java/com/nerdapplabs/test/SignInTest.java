package com.nerdapplabs.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.SignInPageObject;

public class SignInTest extends SignInPageObject {
	
/// create the object of signInPageObject to access the methods 		
 SignInPageObject signInPage = new SignInPageObject(driver);
  
 //create constructor for initialization
 public SignInTest()
 {
	 
 }
       @Test
       //check to pass login in page title
   	    public void verifyLoginInPageTitle() {   
    	 Assert.assertTrue("Login Page title is not displayed", signInPage.verifySignInPageTitle() );
        }
       
       @Test
       //check to pass email field is present
       public void verifyEmailFieldIsPresent() {
       Assert.assertTrue(signInPage.verifyEmailField());
       }
       
       @Test
       //check to pass password filed is displayed
        public void verifyPasswordTextFieldIsDisplayed(){
    	Assert.assertTrue(signInPage.verifyPasswordField());
       }
       
       @Test
       //check to pass email placeholder
       public void verifyEmailPlaceholder() {
    	 Assert.assertTrue("email placeholder is not present",signInPage.verifyUserPlaceholder());  
       }
       
       @Test
       //check to pass password placeholder
       public void verifyPwdPlcaeHolder(){
    	   Assert.assertTrue("password placeholder is not present",signInPage.verifyPasswordPlaceholder() );
       }
       @Test
       // check to pass login button is displayed
        public void verifyLoginButtonIsDisplayed() {
    	   Assert.assertTrue("Login button is not displayed", signInPage.verifyLoginButtonIsPresent() );
       }
       
       @Test 
       //check to pass sign in button is enabled
        public void verifySignInButtonIsEnabled(){
    	   Assert.assertTrue("Sign button is not enabled", signInPage.verifyLoginButtonIsEnabled()  );    
       }
       
       @Test
       //check to pass sign in button is clicked or not
       public void verifySignInButtonIsClickable(){
    	   Assert.assertTrue("Sign button is not clickable", signInPage.verifyLoginButtonIsClicked()  );    
       }
	  
       @Test
       //check to pass forgot password link is displayed
       public void verifyForgotPasswordLinkIsDisplayed(){
    	   Assert.assertTrue("Forgot password link is invisible", signInPage.verifyForgotPasswordLinkIsPresent());
       }
       
       @Test
       //check to pass forgot link is clicked or not
       public void verifyForgotLinkIsClicked(){
    	   Assert.assertTrue("Forgot password link is not clickable", signInPage.verifyForgotPasswordLinkIsClickable());  
    	   
       }
       @Test
       //check to pass email text box is empty or not
       public void verifyEmailTextBoxIsEmpty() throws IOException{
    	   Assert.assertTrue("Email field is not empty", signInPage.verifyEmailEmpty());  
       }
       
      /* @Test
       public void verifyEmailPwdTextAlignment(){
    	   Assert.assertTrue("Email and password don't have proper alignment",signInPage.verifyEmailAndPasswordTextAlignment());
    	   
       }*/
     
      
}
		 
       


