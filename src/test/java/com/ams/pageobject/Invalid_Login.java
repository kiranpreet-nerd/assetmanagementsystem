package com.ams.pageobject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ams.testsetup.SetUp;

@RunWith(Parameterized.class)
public class Invalid_Login extends SetUp {

	private String username;
	private String password;

	static List<String[]> dataSource;
	static By emailTextBox = By.name("email");
	static By passwordTextBox = By.name("password");
	By loginBtn = By.name("loginbutton");
	By logout = By.linkText("LOGOUT");
	By error_msg = By.cssSelector("div[class= 'redalert']");

	// the testData() method, which will return test data from the CSV file as a
	// collection. This method internally calls the getTestData() method. Also
	// add a constructor to Invalid_Login class, which will be used by
	// the test runner to pass the parameters to the Invalid_Login class
	// instance
	@Parameterized.Parameters
	public static Collection<String[]> testData() throws IOException {
		return getTestData("./datasource/Invalid_Login.csv");
	}

	public Invalid_Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// to reads a CSV file and returns the data in the collection.
	public static Collection<String[]> getTestData(String fileName) throws IOException {
		List<String[]> records = new ArrayList<String[]>();
		String record;
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		while ((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		return records;
	}

	// to verify the login functionality with error message while invalid data
	// given to it
	public boolean Invalid_LoginFunction() {
		// find email text box and send email
		driver.findElement(emailTextBox).sendKeys(username);
		// find password text box and send password
		driver.findElement(passwordTextBox).sendKeys(password);
		// find login button
		WebElement login = driver.findElement(loginBtn);
		// click on login button
		login.click();
		if (driver.findElement(error_msg).isDisplayed()) {
			return true;
		}
		return false;
	}
}