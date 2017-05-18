package com.ams.testsetup;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.nerdapplabs.test.TestDataReader;

public class Open_EmailForConfirmation {
	
	//public static WebDriver driver;
	public static final String url = "http://nerdapplabs.com/mail/";

	public static By email = By.name("_user");
	public static By pwd = By.name("_pass");
	public static By login = By.cssSelector("input[class ='button mainaction']");
	
	static List<String[]> dataSource;
	static String username;
	static String password;
	
	public static void Open_Mail(WebDriver driver) throws InterruptedException {

		// To set the path of chrome driver
		System.setProperty("webdriver.chrome.driver", "libs/chromedriver");
		// Create a new instance of the Chrome driver
		driver = new ChromeDriver();
		// To tell the driver that wait for 10 seconds to navigate to link
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver navigate to given link
		driver.navigate().to(url);
		try {
			dataSource = TestDataReader.readData("Mail_Open.csv");
			String[] userNameData = dataSource.get(0);
			username = userNameData[0];
			String[] passwordData = dataSource.get(1);
			password = passwordData[0];
			// find email text box and send email
			driver.findElement(email).sendKeys(username);
			// find password text box and send password
			driver.findElement(pwd).sendKeys(password);
			// find login button and click on it
			driver.findElement(login).click();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}