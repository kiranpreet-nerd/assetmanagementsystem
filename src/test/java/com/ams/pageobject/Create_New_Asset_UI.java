package com.ams.pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ams.testsetup.SetUpLogin;

public class Create_New_Asset_UI extends  SetUpLogin{

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By logout = By.linkText("LOGOUT");
	By create_title = By.tagName("h3");
	By company_Field = By.cssSelector("select[id ='sell'][name = 'company']");
	By asset_Type = By.cssSelector("select[name ='assettype']");
	By asset_tag = By.name("tag");
	By asset_name = By.name("model");
	By asset_status = By.name("status");
	By serial_number = By.name("serialnumber");
	By purchase_date = By.name("purchasedate");
	By supplier = By.name("supplier");
	By supplier_contact = By.name("suppliercontact");
	By order_number = By.id("ordernumber");
	By purchase_cost = By.id("purchasecost");
	By asset_warranty = By.name("warranty");
	By asset_quantity = By.id("quantity");
	By total_Cost = By.name("totalcost");
	By asset_saveBtn = By.name("saveassetbutton");
	By add_asset = By.linkText("ADD ASSET");
	By add_new_company = By.linkText("New");
	By add_new_model = By.cssSelector("a[id ='dlabel']");
	By asset_model = By.linkText("Asset Model");
	By accessory_model = By.linkText("Accessory Model");
	By consumable_model = By.linkText("Consumable Model");
	By assets_list = By.linkText("ASSETS LIST");

	static List<String[]> dataSource;
	static String username;
	static String password;

	
	// to verify "ADD_ASSET" link is visible to admin
	public boolean verifyAddAssetLinkDisplayed() {
	
			if (driver.findElement(add_asset).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify "ADD_ASSET" is enabled
	public boolean verifyAddAssetLinkEnabled() {
		
			if (driver.findElement(add_asset).isEnabled()) {
				return true;
			}
		return false;
	}

	// to verify "ADD_ASSET" is clicked
	public boolean verifyAddAssetLinkClicked() {

			// find "assets_list" and get text from it store into string
			// variable

			// click on add_asset link
			driver.findElement(add_asset).click();
			String assetList = driver.findElement(assets_list).getText();
			if (assetList.equals("ASSETS LIST")) {
				return true;	
		}
		return false;
	}

	// to verify "Create New Asset" title is visible
	public boolean verifyCreateAssetTitle() {

			driver.findElement(add_asset).click();
			String titleText = driver.findElement(create_title).getText();
			if (titleText.equals("Create New Asset")) {
				return true;
			}
		return false;
	}

	// to verify company field is visible
	public boolean verifyCompanyFieldVisible() {

			driver.findElement(add_asset).click();
			if (driver.findElement(company_Field).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify by default company drop-down box is empty
	public boolean verifyCompanyFieldEmpty() {

			driver.findElement(add_asset).click();
			WebElement CompanyField = driver.findElement(company_Field);
			Select company_drop = new Select(CompanyField);
			List<WebElement> optionValues = company_drop.getOptions();
			if (optionValues.isEmpty()) {
				return true;
			}
		return false;
	}

	// to verify by default "asset-Type" drop-down is not empty
	public boolean verifyAssetTypeDropDownNotEmpty() {

			driver.findElement(add_asset).click();
			WebElement assetType = driver.findElement(asset_Type);
			Select asset_drop = new Select(assetType);
			List<WebElement> optionValues = asset_drop.getOptions();
			if (!(optionValues.isEmpty())) {
				return true;
		}
		return false;
	}

	// to verify asset-type contains None default value
	public boolean verifyAssetTypeDefaultValue() {
	
    		// click on ADD ASSET link
			driver.findElement(add_asset).click();
			// find asset-type drop down and and its values and then get the
			// text
			WebElement assetType = driver.findElement(asset_Type);
			Select drop = new Select(assetType);
			List<WebElement> options = drop.getOptions();
			int count = options.size();
			for (int i = 0; i < count; i++) {
				String listvalue = options.get(i).getText();

				// to check default none value is present
				if (listvalue.equals("None"))
					return true;
			}
		return false;
	}

	// to verify "asset Type" drop-down is single select or multiple select
	public boolean verifyDropDownIsSingleSelect() {

			// click on add asset link
			driver.findElement(add_asset).click();
			WebElement assetDrop = driver.findElement(asset_Type);
			Select drop = new Select(assetDrop);
			if (!(drop.isMultiple())) {
				return true;
			}
		return false;
	}

	// to verify to select the option from "asset-type" drop down impact on
	// another "model" drop-drop
	public boolean verifyAssetTypeImpactOnModelDropDown() {
	
			// to click on "ADD ASSET" link
			driver.findElement(add_asset).click();

			// to select value from drop-down
			WebElement assetTypeField = driver.findElement(asset_Type);
			Select assetOption = new Select(assetTypeField);
			assetOption.selectByVisibleText("Asset");

			// to select values from name drop_down
			WebElement assetName = driver.findElement(asset_name);
			Select asset_drop = new Select(assetName);
			List<WebElement> optionValues = asset_drop.getOptions();
			if (!(optionValues.isEmpty())) {
				return true;
			}
		return false;
	}

	// to verify asset tag field is allow to enter the data
	public boolean verifyAssetTagAllowToEnterData() {
	
			// to click on "ADD ASSET" link
			driver.findElement(add_asset).click();

			// find asset tag field
			WebElement assettag = driver.findElement(asset_tag);

			// send tag value
			assettag.sendKeys("L-MAC13-01-0315-04");

			// get value from tag field
			String tag = assettag.getAttribute("value");

			// to check tag field is not empty
			if (!(tag.isEmpty())) {
				return true;
		}
		return false;
	}

	// to verify model drop-down is empty
	public boolean verifyModelDrop_DownEmpty() {
		
	    	// to click on "ADD ASSET" link
			driver.findElement(add_asset).click();

			// to select options from asset-type drop down
			WebElement assetNype = driver.findElement(asset_name);
			Select dropDown = new Select(assetNype);
			List<WebElement> optionValues = dropDown.getOptions();

			// to check model drop-down is empty
			if (optionValues.isEmpty()) {
				return true;
			}
		return false;
	}

	// to verify status field is visible
	public boolean verifyStatusFieldVisible() {
	
			// click on ADD ASSET link
			driver.findElement(add_asset).click();
			if (driver.findElement(asset_status).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify by default status drop-down is empty
	public boolean verifyStatusFieldEmpty() {
		
			// to click on ADD ASSET link
			driver.findElement(add_asset).click();

			// to select options list from status drop-down
			WebElement assetStatus = driver.findElement(asset_status);
			Select status_drop = new Select(assetStatus);
			List<WebElement> optionValues = status_drop.getOptions();

			// to check option list of status drop-down is empty
			if (optionValues.isEmpty()) {
				return true;
			}
		return false;
	}

	// to verify serial number field is visible
	public boolean verifySerialFieldDisplayed() {
		
		driver.findElement(add_asset).click();
			if (driver.findElement(serial_number).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify purchase date field is visible
	public boolean verifyPurchaseDateField() {
		
			driver.findElement(add_asset).click();
			if (driver.findElement(purchase_date).isDisplayed()) {
				return true;
		  }
		return false;
	}

	// to verify supplier drop is visible
	public boolean verifySupplierFieldVisible() {
		
			driver.findElement(add_asset).click();
			if (driver.findElement(supplier).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify supplier contact number field is visible to user

	public boolean verifySupplierContactnumberField() {
			driver.findElement(add_asset).click();
			if (driver.findElement(supplier_contact).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify order number field is visible to user
	public boolean verifyOrderFieldVisible() {
			driver.findElement(add_asset).click();
			if (driver.findElement(order_number).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify purchase cost field is visible to user
	public boolean verifyPurchaseFieldVisible() {

			driver.findElement(add_asset).click();
			if (driver.findElement(purchase_cost).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify warranty field is visible
	public boolean verifyWarrantyField() {
	
			driver.findElement(add_asset).click();
			if (driver.findElement(asset_warranty).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify quantity field is visible to user
	public boolean verifyQuantityField() {

			driver.findElement(add_asset).click();
			if (driver.findElement(asset_quantity).isDisplayed()) {
				return true;
			}
		return false;
	}

	// to verify total cost field is visible to user
	public boolean verifyTotalField() {
		
			driver.findElement(add_asset).click();
			if (driver.findElement(total_Cost).isDisplayed()) {
				return true;
		}
		return false;
	}

	// to verify save button is visible to user
	public boolean verifySaveButtonVisible() {
			driver.findElement(add_asset).click();
			if (driver.findElement(asset_saveBtn).isDisplayed()) {
				return true;
		}
		return false;
	}

	// to verify save button is enabled
	public boolean verifySaveButtonEnabled() {
			driver.findElement(add_asset).click();
			if (driver.findElement(asset_saveBtn).isEnabled()) {
				return true;
		}
		return false;
	}
}