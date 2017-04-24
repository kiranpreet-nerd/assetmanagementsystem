package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.SignUpFieldsEmpty;

public class SignUpFieldEmptyTest extends SignUpFieldsEmpty {
	
	@Test
	public void verifyEmailFieldEmpty() {
		Assert.assertTrue("email field is not empty",VerifyEmailEmpty());		
	}

	@Test
	public void verifyFirstNameFieldEmpty() {
		Assert.assertTrue("first name field is not empty",VerifyFirstNameEmpty());		
	}

	@Test
	public void verifyLastNameFieldEmpty() {
		Assert.assertTrue("Last name field is not empty",VerifyLastNameEmpty());			
	}

	@Test
	public void verifyDesignationFieldEmpty() {
		Assert.assertTrue("Designation field is not empty",VerifyDesignationEmpty());			
	}

	@Test
	public void verifyPasswordFieldEmpty() {
		Assert.assertTrue("password field is not empty",VerifyPasswordEmpty());			
	}

	@Test
	public void verifyConfirmPasswordFieldEmpty() {
		Assert.assertTrue(" Confirm password field is not empty",VerifyConfirmpwdEmpty());			
	}
}