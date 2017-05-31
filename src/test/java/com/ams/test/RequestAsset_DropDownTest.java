package com.ams.test;

import org.junit.Assert;
import org.junit.Test;

import com.ams.pageobject.RequestAsset_DropDown;

public class RequestAsset_DropDownTest extends RequestAsset_DropDown {
	
	@Test
	public void verifyAssetTypeDropDownIsSingleSelect() {
		Assert.assertTrue("Asset Type drop-down is not single select",verifyDropDownIsSingleSelect());
	}

	@Test
	public void verifyAssetDefaultDropDownValue() {
		Assert.assertTrue("Asset Type drop-down doesn't contains default value",verifyDefaultValueOfTypeDropDown());
	}
	
	@Test
	public void verifyAssetTypeOptionsCount() {
		Assert.assertTrue("Asset Type drop-down count is not three",verifyAssetTypeDropDownCount());	
	}
	
	@Test
	public void verifyAssetTypeImpactOnAssetName() {
		Assert.assertTrue("Asset Type drop-down doesn't imapct the asset name drop-down",verifyImpactOnNameDropDown());	
	}
	
	@Test
	public void verifyAssetTypeNoneOptionImpactOnAssetName() {
		Assert.assertTrue("None option of assetType drop-down imapct the asset name drop-down",verifyNoneOptionImpactOnNameDropDown());	
	}
	
	@Test
	public void  verifyDateFieldContainCurrentDate()  {
		Assert.assertTrue("Request-Asset date field doesn't comtains current date",verifyDateFieldContainsCurrentDate() );	
	}
}
