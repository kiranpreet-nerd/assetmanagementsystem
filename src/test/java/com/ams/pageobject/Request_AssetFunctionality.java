package com.ams.pageobject;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ams.testsetup.SetUpLogin;

public class Request_AssetFunctionality extends SetUpLogin {

	static By type = By.name("assettype");
	static By name = By.name("assetname");
	static By quant = By.name("quantity");
	static By reasonTxt = By.name("reason");
	static By requestDate = By.name("requestdate");
	static By requestBtn = By.name("requestbutton");
	static By table = By.cssSelector("table[class ='table table-striped']");
	static By dynamicBtn = By.cssSelector("span[class ='glyphicon glyphicon-trash']");
	static By rows = By.tagName("tr");
	static By tD = By.tagName("td");
	static By tH = By.tagName("th");
	static By logout = By.linkText("LOGOUT");

	// to verify the functionality of request-asset page
	@Test
	public void verifyRequest_AssetFunctionality() {

		// take arrayList object to store the assertion values
		ArrayList<String> expectedList = new ArrayList<String>();

		// add the assertion values into arrayList
		expectedList.add("asset");
		expectedList.add("Laptop");
		expectedList.add("2");

		// take arrayList to store the cell values from the
		// web-table
		ArrayList<String> actualList = new ArrayList<String>();

		// find "asset-type" drop down and select the value
		WebElement assetDrop = driver.findElement(type);
		Select drop = new Select(assetDrop);
		drop.selectByVisibleText("Asset");

		// find "asset-name" drop down and select value
		WebElement assetName = driver.findElement(name);
		Select name = new Select(assetName);
		name.selectByVisibleText("Laptop");

		// find "number of needed asset" field and send number
		WebElement assetCount = driver.findElement(quant);
		assetCount.sendKeys("2");

		// find "reason" text-area field and send reason for request an
		// asset
		WebElement reason = driver.findElement(reasonTxt);
		reason.sendKeys("Required");

		// find request button and click on it
		driver.findElement(requestBtn).click();

		// if requested-row is visible on web page then go inside while loop
		// and check the elements
		while (isTableRowPresent()) {

			try {
				// To locate request-asset table
				WebElement webTable = driver.findElement(table);

				// To locate rows of request-asset table.
				List<WebElement> tableRow = webTable.findElements(rows);

				// Loop will execute till the last row of table
				// Since first row is the header row so start the loop form
				// 1

				for (int rnum = 1; rnum < tableRow.size(); rnum++) {

					// To calculate no of cells In that specific row.
					List<WebElement> tableColns = tableRow.get(rnum).findElements(tD);

					// Loop will execute till the last cell of that specific
					// row.
					for (int rcol = 0; rcol < tableColns.size() - 1; rcol++) {

						// To retrieve text from that specific cell.
						String actualValue = tableColns.get(rcol).getText();

						// add these values of each cell into actual list
						actualList.add(actualValue);

					}
					// to compare the actual arrayList and expected arrayList
					Assert.assertArrayEquals("Request for an asset fails, no row found", actualList.toArray(),
							expectedList.toArray());
					actualList.clear();
					if (isTableRowPresent()) {
						driver.findElement(dynamicBtn).click();
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// to check row is inserted or not
	public boolean isTableRowPresent() {
		WebElement webTable = driver.findElement(table);
		List<WebElement> tableRows = webTable.findElements(rows);
		if (tableRows.size() > 1) {
			return true;
		}
		return false;
	}

	@Override
	public void tearDown() {
		while (isTableRowPresent()) {
			driver.findElement(dynamicBtn).click();
		}
		driver.findElement(logout).click();
		super.tearDown();

	}
}