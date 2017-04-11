package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.ams.testsetup.SetUp;
import com.nerdapplabs.test.csvPageObject;

public class csvTestCases extends csvPageObject{
	
	 public csvTestCases(WebDriver driver2) {
		 SetUp.driver =  driver2;
		 	 
	 }
	 public csvTestCases(){
		 
	 }

	csvTestCases csvTestCase = new csvTestCases(driver);
	
	@Test
	public void verifySignFunctionality() {
		
		//Assert.assertTrue("Sign In failed", ((csvPageObject) csvTestCase). verifyLoginWithMultipleTestData());  
		
	}
}
