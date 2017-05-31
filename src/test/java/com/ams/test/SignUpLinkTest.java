package com.ams.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.SignUpLink;

public class SignUpLinkTest extends SignUpLink {

	@Test
	public void verifySignUpLinkIsDisplayed() {
		Assert.assertTrue("signup link is not clicked", verifySignuplinkPresent());
	}

	// to check sign up link enabled is pass or not
	@Test
	public void verifySignUpLinkIsEnabled() {
		Assert.assertTrue("signup link is not clicked", verifySignUpLinkEnabled());
	}

	// to check sign up link clicked is pass or not
	@Test
	public void verifySignUpLinkIsClicked() {
		Assert.assertTrue("signup link is not clicked", verifySignUpClicked());
	}

}
