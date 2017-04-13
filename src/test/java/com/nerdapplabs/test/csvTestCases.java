package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.nerdapplabs.test.Test_Login;

public class csvTestCases extends Test_Login{
	
	
  private WebDriver driver;
  csvTestCases csvObject = new csvTestCases(driver);
	public csvTestCases(WebDriver driver2) {
			
	}
	//csvTestCases csvTestCase = new csvTestCases(driver);
	
	@Test
	public void verifySignFunctionality()throws ArrayIndexOutOfBoundsException{
		

		Assert.assertTrue("Sign In failed", csvObject.LoginFunctionality());  
		
	}
}
