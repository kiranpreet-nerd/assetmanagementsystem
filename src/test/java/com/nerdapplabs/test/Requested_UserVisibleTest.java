package com.nerdapplabs.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import com.ams.pageobject.Registered_UserVisibility;
import com.ams.testsetup.LoginPage;

public class Requested_UserVisibleTest extends Registered_UserVisibility {
	
	public Requested_UserVisibleTest(String name, String email, String destination, String Role) {
		super(name, email, destination, Role);
	}
	// expectedList to store expected values
	ArrayList<String> expectedList = new ArrayList<String>();
	// actualList from webTable
	ArrayList<String> actualList = new ArrayList<String>();
	
	@Test
	// to verify registered user visible to super_admin having role USER
	public void verifyRegisteredUserVisibilityToSuper_Admin() {
		// login the application
		LoginPage.login_User();
		// add values into expected list
		expectedList.add(name);
		expectedList.add(email);
		expectedList.add(destination);
		expectedList.add(Role);
		WebElement webTable = driver.findElement(table);
		List<WebElement> tableRow = webTable.findElements(rows);
		for (int rnum = 1; rnum < tableRow.size(); rnum++) {
			List<WebElement> tableColns = tableRow.get(rnum).findElements(cols);
			for (int rcol = 0; rcol < tableColns.size() - 1; rcol++) {
				String actualValue = tableColns.get(rcol).getText();
				actualList.add(actualValue);
			}
			Assert.assertArrayEquals("Request for an asset fails, no row found", actualList.toArray(),
					expectedList.toArray());
		}
	}
	@Override
	public void tearDown() {
		driver.findElement(logout).click();	
		super.tearDown();
	}
}