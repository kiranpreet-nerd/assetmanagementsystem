package com.ams.pageobject;

import org.openqa.selenium.By;
import com.ams.testsetup.SetUpLogin;

public class LoginFunctionality extends SetUpLogin  {
	
public By logout = By.linkText("LOGOUT");
	
	@Override
	public void setUp() {
		try {
			super.setUp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	//to check login functionality
	public boolean loginFunction() {
		
		String lgout = driver.findElement(logout).getText();
		String logged = "LOGOUT";
		if (lgout.equals(logged)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void tearDown() {	
	   driver.findElement(logout).click();
	   super.tearDown();
	}
}