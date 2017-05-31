package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.SignUp_Validations;

public class SignUp_ValidationTest extends SignUp_Validations {

	@Test
	public void VerifyEmailEmptyValidation() {
		Assert.assertTrue("email empty validation is not there", verifyEmailValidate());
	}

	@Test
	public void VerifyFirstNameEmptyValidation() {
		Assert.assertTrue("first Name empty validation is not there", verifyFirstNameValidate());
	}

	@Test
	public void VerifyLastNameEmptyValidation() {
		Assert.assertTrue("Last Name empty validation is not applied", verifyLastNameValidate());
	}

	@Test
	public void VerifyDesignationEmptyValidation() {
		Assert.assertTrue("Designation empty validation is not applied", verifyDesignationValidate());
	}

	@Test
	public void VerifyPasswordEmptyValidation() {
		Assert.assertTrue("Password empty validation is not applied", verifyPasswordValidate());
	}

	@Test
	public void VerifyConfirmPwdEmptyValidation() {
		Assert.assertTrue("confirm password empty validation is not applied", verifyConfirmPwdValidate());
	}
}