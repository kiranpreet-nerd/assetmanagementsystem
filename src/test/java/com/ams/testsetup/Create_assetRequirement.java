package com.ams.testsetup;

import org.junit.Test;
import org.openqa.selenium.By;

public class Create_assetRequirement extends SetUp {

	By comapnyLink = By.linkText("New Company");
	By newModel = By.linkText("New");
	By newStatus = By.linkText("New Status");
	By add_supplierLink = By.linkText("New Supplier");
	By add_assetLink = By.linkText("ADD ASSET");
	By company_Field = By.name("company");
	By add_CompanyBtn = By.name("addcompanybutton");
	By model_Field = By.name("model");
	By addModelBtn = By.className("addmodelbutton");	
	By status_Field = By.name("status");
	By status_Btn = By.name("addstatusbutton");
	By supplier_Field = By.name("supplier");
	By supplier_Btn = By.name("addsupplierbutton");
	
	@Test
	public void requirement() {
		
	 //login an application as admin
	AdminLogin.login_Admin();
	
	//to add company name into company drop down
	driver.findElement(add_assetLink).click();
	driver.findElement(comapnyLink).click();
	driver.findElement(company_Field).sendKeys("mac");
	driver.findElement(add_CompanyBtn).click();
	
	//to add status into status drop down
	driver.findElement(add_assetLink).click();
	driver.findElement(newStatus).click();
	driver.findElement(status_Field).sendKeys("Passed");
	driver.findElement(status_Btn).click();
	
	//to add supplier into supplier drop down
	driver.findElement(add_assetLink).click();
	driver.findElement(add_supplierLink).click();
	driver.findElement(supplier_Field).sendKeys("supplier");
	driver.findElement(supplier_Btn).click();
	
	//to add new model into model drop-down, click on new link, select asset model then add
	
}
}
