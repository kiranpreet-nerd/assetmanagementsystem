package com.nerdapplabs.test;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.SignInPageObject;

public class SignInTest extends SignInPageObject {

	@Test
	// check to pass login in page title
	public void verifyLoginInPageTitle() {
		Assert.assertTrue("Login Page title is not displayed", SignInPageObject.verifySignInPageTitle());
	}

	@Test
	// check to pass email field is present
	public void verifyEmailFieldIsPresent() {
		Assert.assertTrue(SignInPageObject.verifyEmailField());
	}

	@Test
	// check to pass password filed is displayed
	public void verifyPasswordTextFieldIsDisplayed() {
		Assert.assertTrue(SignInPageObject.verifyPasswordField());
	}

	@Test
	// check to pass email placeholder
	public void verifyEmailPlaceholder() {
		Assert.assertTrue("email placeholder is not present", SignInPageObject.verifyUserPlaceholder());
	}

	@Test
	// check to pass password placeholder
	public void verifyPwdPlcaeHolder() {
		Assert.assertTrue("password placeholder is not present", SignInPageObject.verifyPasswordPlaceholder());
	}

	@Test
	// check to pass login button is displayed
	public void verifyLoginButtonIsDisplayed() {
		Assert.assertTrue("Login button is not displayed", SignInPageObject.verifyLoginButtonIsPresent());
	}

	@Test
	// check to pass sign in button is enabled
	public void verifySignInButtonIsEnabled() {
		Assert.assertTrue("Sign button is not enabled", SignInPageObject.verifyLoginButtonIsEnabled());
	}

	@Test
	// check to pass sign in button is clicked or not
	public void verifySignInButtonIsClickable() {
		Assert.assertTrue("Sign button is not clickable", SignInPageObject.verifyLoginButtonIsClicked());
	}

	@Test
	// check to pass forgot password link is displayed
	public void verifyForgotPasswordLinkIsDisplayed() {
		Assert.assertTrue("Forgot password link is invisible", SignInPageObject.verifyForgotPasswordLinkIsPresent());
	}

	@Test
	// check to pass forgot link is clicked or not
	public void verifyForgotLinkIsClicked() {
		Assert.assertTrue("Forgot password link is not clickable",
				SignInPageObject.verifyForgotPasswordLinkIsClickable());
	}

	@Test
	// check to pass email text box is empty or not
	public void verifyEmailTextBoxIsEmpty() throws IOException {
		Assert.assertTrue("Email field is not empty", SignInPageObject.verifyEmailEmpty());
	}
}