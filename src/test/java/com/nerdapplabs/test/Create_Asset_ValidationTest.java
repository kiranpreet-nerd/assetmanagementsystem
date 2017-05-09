package com.nerdapplabs.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.Create_Asset_Validations;

public class Create_Asset_ValidationTest extends Create_Asset_Validations {

	@Test
	public void verifyCompanyFieldValidation() {
		Assert.assertTrue("On company field there is no validation",verifyCompanyValidation() );
	}
	
	@Test
	public void verifyAsset_TypeFieldValidation() {
		Assert.assertTrue("On Asset-Type field there is no validation",verifyAssetTypeValidation() );
	}
	
	@Test
	public void verifyAsset_TagFieldValidation() {
		Assert.assertTrue("On Asset-Tag field there is no validation",verifyAssetTagValidation() );
	}
	
	@Test
	public void  verifyStatusFieldValidation() {
		Assert.assertTrue("On status field there is no validation", verifyStatusValidation());
	}
	
	@Test
	public void verifySerialNumberFieldValidation() {
		Assert.assertTrue("On serial field there is no validation",verifySerialNumberValidation());
	}
	
	@Test
	public void verifyPurchaseDateFieldValidation() {
		Assert.assertTrue("On purchase date field there is no validation",verifyPurchaseDateValidation());
	}
	
	
	
	
	
}
