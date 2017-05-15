package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.DependsOn;

import com.ams.pageobject.Forgot_Password;

public class Forgot_Password_Test extends Forgot_Password {
	
	@Test
	public void verifyForgotLinkIsClickable() {
		Assert.assertTrue(verifyForgotLinkClickable());
	}

	@Test
	public void verifyEmailFieldIsVisible() {
		Assert.assertTrue("email field is not visible",verifyEmailField());	
	}
	
	@Test
	public void verifyEmailEmptyValidation() {
		Assert.assertTrue("email field is not empty and error message not found",verifyEmailEmptyFieldValidation());	
	}
	
	@Test
	public void verifyGetPwdBtnIsVisibleAndEnabled() {
		Assert.assertTrue("get password button is not visible to user",verifyGetPwdBtnVisibleAndEnabled());	
	}
	
	@Test
	public void verifyEmailFieldPlaceholder()  {
		Assert.assertTrue("email field don't have placeholder",verifyEmailPlaceholder());	
	}
	
	@Test
	public void verifyForgotPwdFunctionlityWorks()  {
		Assert.assertTrue("forgot password functionality fails",verifyForgotPwdFunctionlity());	
	}		
}