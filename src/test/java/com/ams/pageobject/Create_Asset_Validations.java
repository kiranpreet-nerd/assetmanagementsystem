package com.ams.pageobject;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ams.testsetup.SetUpLogin;

public class Create_Asset_Validations extends SetUpLogin {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By logout = By.linkText("LOGOUT");
	By create_title = By.tagName("h3");
	By company_Field = By.cssSelector("select[id ='sell'][name = 'company']");
	By asset_Type = By.cssSelector("select[name ='assettype']");
	By asset_tag = By.cssSelector("input[name ='tag'][value = '']");
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
	By back = By.linkText("BACK");

	static List<String[]> dataSource;
	static String username;
	static String password;

	
	// to verify company field validation and handle alert message
	public boolean verifyCompanyValidation() {
            
			// to click on add asset link
			driver.findElement(add_asset).click();

			// to click on SAVE button
			driver.findElement(asset_saveBtn).click();

			try {
				// to locate the alert
				Alert al = driver.switchTo().alert();

				// to get text from alert and store into string variable
				String alertText = al.getText();

				if (alertText.equals("company required"))

					// to click on OK button to handle the alert pop-up
					al.accept();

			} catch (Exception e) {
				e.printStackTrace();

			}
			// to get value from company field and store into string variable
			String compnyField = driver.findElement(company_Field).getAttribute("value");

			// to check company field is empty and alert message contains
			if (compnyField.isEmpty()) {
				return true;
			}
	
		return false;
	}

	// to verify Asset-type validation and handle alert message

	public boolean verifyAssetTypeValidation() {
			// to click on add asset link
			driver.findElement(add_asset).click();

			// to click on SAVE button
			driver.findElement(asset_saveBtn).click();

			try {
				// to locate the alert
				Alert al = driver.switchTo().alert();

				// to get text from alert and store into string variable
				String alertText = al.getText();

				if (alertText.equals("asset type required"))

					// In oder to handle the alert message click on OK button
					al.accept();

			} catch (Exception e) {
				e.printStackTrace();
			}
			// to get value from asset-type field and store into string variable
			String assetType = driver.findElement(asset_Type).getAttribute("value");

			// to check asset type field is empty
			if (assetType.equals("None")) {
				return true;
			}

		return false;
	}

	// to verify Asset-tag field validation
	public boolean verifyAssetTagValidation() {
		
			// to click on add asset link
			driver.findElement(add_asset).click();

			// to select any option from company field
			WebElement compnyField = driver.findElement(company_Field);
			Select company = new Select(compnyField);
			company.selectByVisibleText("dell");

			// to select any option from asset type
			WebElement assetTypeField = driver.findElement(asset_Type);
			Select assetOption = new Select(assetTypeField);
			assetOption.selectByVisibleText("Asset");

			// to select any option corresponding to"ASSET-TYPE" from model
			// field
			WebElement assetName = driver.findElement(asset_name);
			Select assetValue = new Select(assetName);
			assetValue.selectByVisibleText("Laptop");

			// to click on SAVE button
			driver.findElement(asset_saveBtn).click();

			try {
				// to locate the alert using switch statement
				Alert al = driver.switchTo().alert();

				// to get text from alert and store into string variable
				String alertText = al.getText();

				// to check actual text equals to expected text
				if (alertText.equals("tag required"))

					// In oder to handle the alert message click on OK button
					al.accept();

			} catch (Exception e) {
				e.printStackTrace();
			}
			// to get value from asset-tag field and store into string variable
			String assetTag = driver.findElement(asset_tag).getAttribute("value");

			// to check asset tag field is empty
			if (assetTag.isEmpty()) {
				return true;
			}
		return false;
	}

	// to verify status field validation
	public boolean verifyStatusValidation() {
	
			// to click on add asset link
			driver.findElement(add_asset).click();

			// to select any option from company field
			WebElement compnyField = driver.findElement(company_Field);
			Select company = new Select(compnyField);
			company.selectByVisibleText("dell");

			// to select any option from asset type
			WebElement assetTypeField = driver.findElement(asset_Type);
			Select assetOption = new Select(assetTypeField);
			assetOption.selectByVisibleText("Asset");

			// to select any option corresponding to"ASSET-TYPE" from model
			// field
			WebElement assetName = driver.findElement(asset_name);
			Select assetValue = new Select(assetName);
			assetValue.selectByVisibleText("Laptop");

			// find tag field and send model tag
			driver.findElement(asset_tag).sendKeys("L-MAC13-01-0315-04");

			// to click on SAVE button
			driver.findElement(asset_saveBtn).click();

			try {
				// to locate the alert using switch statement
				Alert al = driver.switchTo().alert();

				// to get text from alert and store into string variable
				String alertText = al.getText();

				// to check actual text equals to expected text
				if (alertText.equals("status required"))

					// In oder to handle the alert message click on OK button
					al.accept();

			} catch (Exception e) {
				e.printStackTrace();
			}
			// to get value from status field and store into string variable
			String statusValue = driver.findElement(asset_status).getAttribute("value");

			// to check status field is empty
			if (statusValue.isEmpty()) {
				return true;
			}
		return false;
	}

	// to verify serial number validation
	public boolean verifySerialNumberValidation() {
		
			// to click on add asset link
			driver.findElement(add_asset).click();

			// to select any option from company field
			WebElement compnyField = driver.findElement(company_Field);
			Select company = new Select(compnyField);
			company.selectByVisibleText("dell");

			// to select any option from asset type
			WebElement assetTypeField = driver.findElement(asset_Type);
			Select assetOption = new Select(assetTypeField);
			assetOption.selectByVisibleText("Asset");

			// to select any option corresponding to"ASSET-TYPE" from model
			// field
			WebElement assetName = driver.findElement(asset_name);
			Select assetValue = new Select(assetName);
			assetValue.selectByVisibleText("Laptop");

			// find tag field and send model tag
			driver.findElement(asset_tag).sendKeys("L-MAC13-01-0315-04");

			// to select status from status drop-down
			WebElement assetStatus = driver.findElement(asset_status);
			Select assetstatus = new Select(assetStatus);
			assetstatus.selectByVisibleText("pending");

			// to click on SAVE button
			driver.findElement(asset_saveBtn).click();

			try {
				// to locate the alert using switch statement
				Alert al = driver.switchTo().alert();

				// to get text from alert and store into string variable
				String alertText = al.getText();

				// to check actual text equals to expected text
				if (alertText.equals("serial number required"))

					// In oder to handle the alert message click on OK button
					al.accept();

			} catch (Exception e) {
				e.printStackTrace();
			}
			// to get value from serial field and store into string variable
			String serialValue = driver.findElement(serial_number).getAttribute("value");

			// to check serial-number field is empty
			if (serialValue.isEmpty()) {
				return true;
			}
		return false;
	}

	// to verify purchase date field validation
	public boolean verifyPurchaseDateValidation() {
		
			// to click on add asset link
			driver.findElement(add_asset).click();

			// to select any option from company field
			WebElement compnyField = driver.findElement(company_Field);
			Select company = new Select(compnyField);
			company.selectByVisibleText("dell");

			// to select any option from asset type
			WebElement assetTypeField = driver.findElement(asset_Type);
			Select assetOption = new Select(assetTypeField);
			assetOption.selectByVisibleText("Asset");

			// to select any option corresponding to"ASSET-TYPE" from model
			// field
			WebElement assetName = driver.findElement(asset_name);
			Select assetValue = new Select(assetName);
			assetValue.selectByVisibleText("Laptop");

			// find tag field and send model tag
			driver.findElement(asset_tag).sendKeys("L-MAC13-01-0315-04");

			// to select status from status drop-down
			WebElement assetStatus = driver.findElement(asset_status);
			Select assetstatus = new Select(assetStatus);
			assetstatus.selectByVisibleText("pending");

			// to find serial number field and send serial number
			driver.findElement(serial_number).sendKeys("mac1309");

			// to click on SAVE button
			driver.findElement(asset_saveBtn).click();

			try {
				// to locate the alert using switch statement
				Alert al = driver.switchTo().alert();

				// to get text from alert and store into string variable
				String alertText = al.getText();

				// to check actual text equals to expected text
				if (alertText.equals("purchase date required"))

					// In oder to handle the alert message click on OK button
					al.accept();

			} catch (Exception e) {
				e.printStackTrace();
			}
			// to get value from purchase-date field and store into string variable
			String purchaseValue = driver.findElement(purchase_date).getAttribute("value");

			// to check purchase-date field is empty
			if (purchaseValue.isEmpty()) {
				return true;
			}
		return false;
	}
	
	@Override
	public void tearDown() {
		driver.findElement(back).click();
		driver.findElement(logout).click();
		super.tearDown();
	}

}