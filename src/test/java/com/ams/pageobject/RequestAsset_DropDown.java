package com.ams.pageobject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ams.testsetup.SetUpLogin;

public class RequestAsset_DropDown extends SetUpLogin {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By logout = By.linkText("LOGOUT");
	static By type = By.id("ddl");
	static By name = By.name("assetname");
	static By date = By.name("requestdate");
	static By textArea = By.name("reason");
	
	@Override
	public void setUp() {
		try {
			super.setUp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// to verify "asset Type" drop-down is single select or multiple select
	public boolean verifyDropDownIsSingleSelect() {
		
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			if (!(drop.isMultiple())) {
				return true;
			}
		return false;
	}

	// verify "asset type" drop-down options count is 4
	public boolean verifyAssetTypeDropDownCount() {
	
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			List<WebElement> options = drop.getOptions();
			int count = options.size();
			if (count == 4) {
				return true;
			}
		return false;
	}

	// to verify default value in "asset Type" drop down

	public boolean verifyDefaultValueOfTypeDropDown() {

			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			List<WebElement> options = drop.getOptions();
			int count = options.size();
			for (int i = 0; i < count; i++) {
				 String listvalue = options.get(i).getText();
				if (listvalue.equals("None")) {
					return true;
				}
		}
		return false;
	}

	// verify to select the option from "asset type" may impact on another
	// drop-down
	public boolean verifyImpactOnNameDropDown() {
		
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			drop.selectByVisibleText("Asset");
			String nameDrop = driver.findElement(name).getAttribute("value");
			if (!(nameDrop.isEmpty())) {
				return true;
			}

		return false;
	}

	// verify "None" value of "asset type" does't impact on assetName drop-down
	public boolean verifyNoneOptionImpactOnNameDropDown() {
		
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			drop.selectByVisibleText("None");
			String nameDrop = driver.findElement(name).getAttribute("value");
			if (nameDrop.isEmpty()) {
				return true;
			}
	
		return false;
	}

	// to verify date field having current date
	public boolean verifyDateFieldContainsCurrentDate() {
			
			//to find date value from date field and store into string variable
			String assetDate = driver.findElement(date).getAttribute("value");
			
			//create object of dateFormat class and pass required format as argument
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			//to get current date make object of Date class
			Date currentDate = new Date();
			
			// to access current date in required format and store into string variable
			String currentdate = dateFormat.format(currentDate);
			
			if (assetDate.equals(currentdate)) {
				return true;
			}
		return false;
	}
	
	@Override
	public void tearDown() {
		driver.findElement(logout).click();
		super.tearDown();
		
	}
}