package com.ams.testsetup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ams.test.TestDataReader;

public class SetUpLogin {

	public static WebDriver driver;
	public static final String url = "http://localhost:8090/login";

	public static By emailTextBox = By.name("email");
	public static By passwordTextBox = By.name("password");
	public By loginBtn = By.name("loginbutton");

	static List<String[]> dataSource;
	static String username;
	static String password;
	
	/*
	 * @ this method is to verify login functionality with multiple test data
	 * through csv file exception IOException on input error
	 * 
	 * @param username to get value of email from string array
	 * 
	 * @param password to get value of password from string array
	 * 
	 * @array dataSource to read test data from csv file
	 * 
	 * @array userNameData to get values from dataSource
	 */
	@Before
	public void SetUp() throws InterruptedException {

		// To set the path of chrome driver
		System.setProperty("webdriver.chrome.driver", "libs/chromedriver");
		// Create a new instance of the Chrome driver
		driver = new ChromeDriver();
		// To tell the driver that wait for 10 seconds to navigate to link
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver navigate to given link
		driver.navigate().to(url);
		try {
			dataSource = TestDataReader.readData("Test_Login.csv");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		// to close the driver
		driver.quit();

	}
}