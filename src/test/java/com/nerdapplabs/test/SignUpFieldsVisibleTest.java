package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.SignUpFieldsVisible;

public class SignUpFieldsVisibleTest extends SignUpFieldsVisible {

	@Test
	public void verifyEmailField() {
		Assert.assertTrue("email field is not visible", verifyEmailTextBox());
	}

	@Test
	public void VerifyFirstNameField() {
		Assert.assertTrue("firstname field is not visible", verifyFirstNameTextBox());
	}

	@Test
	public void VerifyLastNameField() {
		Assert.assertTrue("lastname field is not visible", verifyLastNameTextBox());
	}

	@Test
	public void VerifyDesignationField() {
		Assert.assertTrue("designation field is not visible", verifyDesignationTextBox());
	}

	@Test
	public void VerifyRoleField() {
		Assert.assertTrue("Role field is not visible", verifyRoleTextBox());
	}

	@Test
	public void VerifyPasswordField() {
		Assert.assertTrue("password field is not visible", verifyPasswordTextBox());
	}

	@Test
	public void ConfirmPwdField() {
		Assert.assertTrue("ConfirmPwd field is not visible", verifyConfirmPwdTextBox());
	}
}