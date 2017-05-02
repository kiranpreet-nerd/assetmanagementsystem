package com.ams.pageobject;

import java.io.IOException;
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
		drop.selectByVisibleText("Asset");

		// find "asset-name" drop down and select value
		WebElement assetName = driver.findElement(name);
		Select name = new Select(assetName);
		name.selectByVisibleText("laptop");

		// find "number of needed asset" field and send number
		WebElement assetCount = driver.findElement(quant);
		assetCount.sendKeys("1");

		// find "reason" text-area field and send reason for request an
		// asset
		WebElement reason = driver.findElement(reasonTxt);
		reason.sendKeys("Required");

		// find request button and click on it
		driver.findElement(requestBtn).click();

		while (isTableRowInserted()) {

			// find request asset table
			WebElement webTable = driver.findElement(table);

			// find table rows and put into list
			List<WebElement> tableRow = webTable.findElements(rows);

			// Access table data
			for (int r = 1; r <= tableRow.size(); r++) {

				// find table data element and put into list
				List<WebElement> tabledata = tableRow.get(r).findElements(tD);

				// to access data from each cell and store into string
				// variable
				for (int cnum = 0; cnum < tabledata.size(); cnum++) {
					String element = tabledata.get(cnum).getText();
					// validating the each cell data
					if (element.equals("Asset"))
						return true;
					if (element.equals("laptop"))
						return true;
					if (element.equals("1"))
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
public void verifyFunctionality() {
	Assert.assertTrue("Request for an asset failed", verifyRequest_AssetFunctionality());
	}

}
