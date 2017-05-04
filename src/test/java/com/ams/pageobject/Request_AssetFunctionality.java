package com.ams.pageobject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.ams.testsetup.SetUp;
import com.nerdapplabs.test.TestDataReader;

public class Request_AssetFunctionality extends SetUp {

	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	static By loginBtn = By.name("loginbutton");
	static By emailReq = By.name("email");
	static By logout = By.linkText("LOGOUT");
	static By type = By.name("assettype");
	static By name = By.name("assetname");
	static By quant = By.name("quantity");
	static By reasonTxt = By.name("reason");
	static By requestDate = By.name("requestdate");
	static By requestBtn = By.name("requestbutton");
	static By table = By.cssSelector("table[class ='table table-striped']");
	static By rows = By.tagName("tr");
	static By tD = By.tagName("td");
	static By tH = By.tagName("th");

	static List<String[]> dataSource;
	static String username;
	static String password;

	// to login the application
	public static boolean loginFunction() throws IOException {
		try {
			dataSource = TestDataReader.readData("Test_Login.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] userNameData = dataSource.get(0);
		username = userNameData[0];
		String[] passwordData = dataSource.get(1);
		password = passwordData[0];
		// find email text box and send email
		driver.findElement(emailTextBox).sendKeys(username);
		// find password text box and send password
		driver.findElement(passwordTextBox).sendKeys(password);
		// find login button
		WebElement login = driver.findElement(loginBtn);
		// click on login button
		login.click();
		String lgout = driver.findElement(logout).getText();
		String logged = "LOGOUT";
		if (lgout.equals(logged)) {
			return true;
		}
		return false;
	}

	// to verify the functionality of request-asset page
	public boolean verifyRequest_AssetFunctionality() {

		try {
			loginFunction();
			// find "asset-type" drop down and select the value
			WebElement assetDrop = driver.findElement(type);
			Select drop = new Select(assetDrop);
			drop.selectByVisibleText("Accessory");

			// find "asset-name" drop down and select value
			WebElement assetName = driver.findElement(name);
			Select name = new Select(assetName);
			name.selectByVisibleText("mouse");

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
			while (isTableRowInserted()) {

				// take arrayList object to store the assertion values
				ArrayList<String> expectedList = new ArrayList<String>();

				// add the assertion values into arrayList
				expectedList.add("Accessory");
				expectedList.add("mouse");
				expectedList.add("2");

				// take arrayList to store the cell values from the web-table
				ArrayList<String> actualList = new ArrayList<String>();

				// To locate request-asset table
				WebElement webTable = driver.findElement(table);

				// To locate rows of request-asset table.
				List<WebElement> tableRow = webTable.findElements(rows);

				// Loop will execute till the last row of table
				// Since first row is the header row so start the loop form 1
				for (int rnum = 1; rnum < tableRow.size(); rnum++) {

					// To calculate no of cells In that specific row.
					List<WebElement> tableColns = tableRow.get(rnum).findElements(tD);

					// Loop will execute till the last cell of that specific
					// row.
					for (int rcol = 0; rcol < tableColns.size(); rcol++) {

						// To retrieve text from that specific cell.
						String actualValue = tableColns.get(rcol).getText();

						// add these values of each cell into actual list
						actualList.add(actualValue);
					}

					// to compare the actual arrayList and expected arrayList
					if (Arrays.equals(actualList.toArray(), expectedList.toArray())) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// to check row is inserted or not
	public boolean isTableRowInserted() {
		WebElement webTable = driver.findElement(table);
		List<WebElement> tableRows = webTable.findElements(rows);
		int count = tableRows.size();
		System.out.print("count is" + count);
		if (count == 1) {
			return false;
		}
		return true;
	}

	@Test
	public void verifyFunctionalityOfRequestAsset() {
		Assert.assertTrue("Request for an asset failed", verifyRequest_AssetFunctionality());
	}

}
