package com.ams.test;

import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.LoginFunctionality;

public class LoginFunctionality_Test extends LoginFunctionality {
	
	@Test
	public void verifyLoginFunctionality() {
			Assert.assertTrue("failed to logged in", loginFunction());	
	}	
}