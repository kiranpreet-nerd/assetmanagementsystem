package com.ams.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.SignUpPlaceHolder;

public class SignUpPlaceHolderTest extends SignUpPlaceHolder {

	@Test
	public void verifyEmailPlaceHolder() {
		Assert.assertTrue("Email placeholder is not present", verifyEmailPlaceholder());
	}

	@Test
	public void verifyFirstNamePlaceHolder() {
		Assert.assertTrue("firstname placeholder is not present", verifyFirstNamePlaceholder());
	}

	@Test
	public void verifyLastNamePlaceHolder() {
		Assert.assertTrue("LastName placeholder is not present", verifyLastNamePlaceholder());
	}

	@Test
	public void verifyDesignationPlaceHolder() {
		Assert.assertTrue("Designation placeholder is not present", verifyDesignationPlaceholder());
	}

	@Test
	public void verifyPasswordPlaceHolder() {
		Assert.assertTrue("password placeholder is not present", verifyPasswordPlaceholder());
	}

	@Test
	public void verifyConfirmPwdPlaceHolder() {
		Assert.assertTrue("Confirmpwd placeholder is not present", verifyConfirmPwdPlaceholder());
	}

	@Test
	public void verifyRoleFieldNotEmpty() {
		Assert.assertTrue("ROLE field is empty", VerifyRoleIsNotEmpty());
	}

}
