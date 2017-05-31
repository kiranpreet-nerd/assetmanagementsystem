package com.ams.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.Invalid_Login;

public class Invalid_Login_Test extends Invalid_Login{

	public Invalid_Login_Test(String username, String password) {
		super(username, password);	
	}
	
	//to check logged in must be failed
		@Test
		public void verifyInvalidLoginFunctionality() {
			Assert.assertTrue("logged in successfully", Invalid_LoginFunction());

		}

}
