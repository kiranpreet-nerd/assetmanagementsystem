package com.nerdapplabs.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.Test_RequestAssetUI;

public class Test_RequestAssetUITest extends Test_RequestAssetUI {

	@Test
	public void verifyEmailFiedOnReqAssetIsVisible() throws IOException {
		Assert.assertTrue("Email field is not visible", verifyEmailFiedOnReqAssetVisible());
	}

	@Test
	public void verifyAssetTypeFieldIsVisible() throws IOException {
		Assert.assertTrue("Asset type field is not present", verifyAssetTypeFieldPresent());
	}

	@Test
	public void verifyAssetNameFieldIsVisible() throws IOException {
		Assert.assertTrue("Asset name field is not visible ", verifyAssetNameFieldPresent());
	}

	@Test
	public void verifyNumberOfAssetNeededFieldIsPresent() throws IOException {
		Assert.assertTrue("Number of asset needed field is not visible", verifyNumberOfAssetNeededFieldPresent());
	}

	@Test
	public void verifyReasonTxtAreaFieldIsVisible() throws IOException {
		Assert.assertTrue("Reason text area is not displayed", verifyReasonTxtAreaFieldPresent());
	}

	@Test
	public void verifyRequestDateFieldVisible() throws IOException {
		Assert.assertTrue("Request date field is not visible", verifyRequestDateFieldPresent());
	}

	@Test
	public void verifyRequesBtnFieldIsVisible() throws IOException {
		Assert.assertTrue("Request button is not visible", verifyRequesBtnFieldVisible());
	}

	@Test
	public void verifyRequesBtnFieldIsEnabled() throws IOException {
		Assert.assertTrue("Request button is not enabled", verifyRequesBtnFieldEnabled());
	}
}