package com.ams.test;

import org.junit.Assert;
import org.junit.Test;
import com.ams.pageobject.Create_New_Asset_UI;

public class Create_New_Asset_Test extends Create_New_Asset_UI {

	@Test
	public void verifyAddAssetLinkIsDisplayed() {
		Assert.assertTrue("Add assert link is not visible to admin", verifyAddAssetLinkDisplayed());
	}

	@Test
	public void verifyAddAssetLinkIsEnabled() {
		Assert.assertTrue("Add asset link is not enabled", verifyAddAssetLinkEnabled());
	}

	@Test
	public void verifyAddAssetLinkIsClicked() {
		Assert.assertTrue("Add asset link is not clicked", verifyAddAssetLinkClicked());
	}

	@Test
	public void verifyCreateAssetTitleVisible() {
		Assert.assertTrue("Create asset title not Visible", verifyCreateAssetTitle());
	}

	@Test
	public void verifyCompanyFieldIsVisible() {
		Assert.assertTrue("Company field is not visible", verifyCompanyFieldVisible());
	}

	@Test
	public void verifyCompanyFieldIsEmpty() {
		Assert.assertTrue("Company field is not empty", verifyCompanyFieldEmpty());
	}

	@Test
	public void verifyAssetTypeDrop_DownNotEmpty() {
		Assert.assertTrue("Asset type drop down not empty", verifyAssetTypeDropDownNotEmpty());
	}

	@Test
	public void verifyAsset_TypeDefaultValue() {
		Assert.assertTrue("Asset type default value is not visible", verifyAssetTypeDefaultValue());
	}

	@Test
	public void verifyDrop_DownIsSingleSelect() {
		Assert.assertTrue("Asset-type drop down not single select", verifyDropDownIsSingleSelect());
	}

	@Test
	public void verifyAsset_TypeImpactOnModelDropDown() {
		Assert.assertTrue("Asset type drop does not impact on model drop down", verifyAssetTypeImpactOnModelDropDown());
	}

	@Test
	public void verifyAsset_TagAllowToEnterData() {
		Assert.assertTrue("Asset tag not allow to enter the data", verifyAssetTagAllowToEnterData());
	}

	@Test
	public void verifyModelDropDownIsEmpty() {
		Assert.assertTrue("Model tag drop-down  is not empty", verifyModelDrop_DownEmpty());
	}

	@Test
	public void verifyStatusFieldIsVisible() {
		Assert.assertTrue("Status field is not visible", verifyStatusFieldVisible());
	}

	@Test
	public void verifyStatusFieldIsEmpty() {
		Assert.assertTrue("Status field is not empty", verifyStatusFieldEmpty());
	}

	@Test
	public void verifySerialFieldIsDisplayed() {
		Assert.assertTrue("Serial numbet field is not visible to admin", verifySerialFieldDisplayed());
	}

	@Test
	public void verifyPurchase_DateField() {
		Assert.assertTrue("purchase date filed is not visible to admin", verifyPurchaseDateField());
	}

	@Test
	public void verifySupplier_FieldVisible() {
		Assert.assertTrue("Supplier field not visible to user", verifySupplierFieldVisible());
	}

	@Test
	public void verifySupplierContact_numberField() {
		Assert.assertTrue("Supplier contact number field is not visible to admin", verifySupplierContactnumberField());
	}

	@Test
	public void verifyOrderFieldIsVisible() {
		Assert.assertTrue("Order field is not visible to admin", verifyOrderFieldVisible());
	}

	@Test
	public void verifyPurchaseFieldIsVisible() {
		Assert.assertTrue("Purchase field is not visible to admin ", verifyPurchaseFieldVisible());
	}

	@Test
	public void verifyWarrantyFieldVisible() {
		Assert.assertTrue("Warranty field is not visible to admin", verifyWarrantyField());
	}

	@Test
	public void verifyQuantityFieldVisible() {
		Assert.assertTrue("Quantity filed is not visible to admin", verifyQuantityField());
	}

	@Test
	public void verifyTotalFieldVisible() {
		Assert.assertTrue("Total field is not visible to admin", verifyTotalField());
	}

	@Test
	public void verifySaveButtonIsVisible() {
		Assert.assertTrue("Save button is  not visible to admin", verifySaveButtonVisible());
	}

	@Test
	public void verifySaveButtonIsEnabled() {
		Assert.assertTrue("Save button is not enabled", verifySaveButtonEnabled());
	}
}