package com.ams.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import com.ams.pageobject.Add_UserBySuperAdmin;
import com.ams.testsetup.LoginPage;
import com.ams.testsetup.Login_SuperAdmin;


public class Add_UserBySuperTest extends Add_UserBySuperAdmin {

	public Add_UserBySuperTest(String userEmail, String userFirstName, String userLastName, String userDesignation,
			String userPassword, String userConfirmPassword) {
		super(userEmail, userFirstName, userLastName, userDesignation, userPassword, userConfirmPassword);

	}

	// expectedList to store expected values
	ArrayList<String> expectedList = new ArrayList<String>();
	// actualList from webTable
	ArrayList<String> actualList = new ArrayList<String>();

	String expectedData = "./datasource/Super_Expected_List.csv";
	List<String[]> dataSource;
	String user;
	String fullname;
	String designation;
	String role;

	// to get expected values from csv file
	public void getExpectedValues() {
		try {
			TestDataReader.readData(expectedData);

			String[] userData = dataSource.get(0);
			user = userData[0];

			String[] Fullname = dataSource.get(1);
			fullname = Fullname[0];

			String[] LastNameData = dataSource.get(2);
			designation = LastNameData[0];

			String[] Role_UserData = dataSource.get(3);
			role = Role_UserData[0];

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// to verify add user functionality whenever super_admin add the user
	@Test
	public void verify_AddUserFunctionality() {

		// add values into expected list
		expectedList.add(fullname);
		expectedList.add(user);
		expectedList.add(designation);
		expectedList.add(role);

		// login the application by super_admin
		Login_SuperAdmin.login_SuperAdmin();

		// fill the all the fields and click on add button
		driver.findElement(adduser).click();
		driver.findElement(useremail).sendKeys(userEmail);
		driver.findElement(firstname).sendKeys(userFirstName);
		driver.findElement(lastname).sendKeys(userLastName);
		driver.findElement(desgnation).sendKeys(userDesignation);
		driver.findElement(password).sendKeys(userPassword);
		driver.findElement(confrmPassword).sendKeys(userConfirmPassword);
		driver.findElement(role_user).click();
		driver.findElement(addBtn).click();

		/* find inserted row from web-table
		WebElement webTable = driver.findElement(table);
		List<WebElement> tableRow = webTable.findElements(rows);
		for (int rnum = 2; rnum <= tableRow.size(); rnum++) {
			List<WebElement> tableColns = tableRow.get(rnum).findElements(cols);
			for (int rcol = 1; rcol < tableColns.size() - 1; rcol++) {
				String actualValue = tableColns.get(rcol).getText();
				actualList.add(actualValue);
			}
			//Assert.assertArrayEquals("user is not added, no row found",expectedList.toArray(), actualList.toArray());
			
		}*/
	}
}