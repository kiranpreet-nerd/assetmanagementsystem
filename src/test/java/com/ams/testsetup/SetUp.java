package com.ams.testsetup;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetUp {
	
	public static WebDriver driver;
	public static final String url = "http://localhost:8090/login";

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
	public void setUp() throws InterruptedException {

		// To set the path of chrome driver
		System.setProperty("webdriver.chrome.driver", "libs/chromedriver");
		// Create a new instance of the Chrome driver
		driver = new ChromeDriver();
		// To tell the driver that wait for 10 seconds to navigate to link
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver navigate to given link
		driver.navigate().to(url);
	}
	
	@After
	public  void tearDown() {
		// to close the driver
		driver.quit();
		
	}
}