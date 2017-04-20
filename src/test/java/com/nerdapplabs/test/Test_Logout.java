package com.nerdapplabs.test;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Test_Logout extends Test_Login {
	
	//Locator to find the web element on web page
	public By signup = By.linkText("SIGN UP");
	
	//to check logout functionality
		public boolean LogoutFuntion() throws IOException {
			String signUP = driver.findElement(signup).getText();
			if(LoginFunction()) {
				if(signUP.equals("SIGN UP")) {
					return true;
				}
				return true;
			}
			return false;
		}
	
	//check to pass logout functionality test case
	@Test
	public void verifyLogoutFunctionality() throws IOException {
			Assert.assertTrue(LogoutFuntion(), "failed to logout");	
	}
}
