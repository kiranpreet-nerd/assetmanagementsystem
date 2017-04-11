package com.ams.testsetup;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

	public class SetUp {
		
		public static WebDriver driver;
		public static final String url= "http://localhost:8090/login"; 
		/*
	    In order to use chrome driver
	    Go to: http://chromedriver.storage.googleapis.com/index.html?path=2.9/
	    * Download (I'm using mac, hence) chromedriver_mac64.zip
	    * Extract to <Project DIR>/libs folder
	    * @param args Unused.
	    * @return Nothing.
	    * @exception InterruptedException .
	    */
		@Before
		public void setup() throws InterruptedException  {
			//To set the path of chrome driver
			System.setProperty("webdriver.chrome.driver", "libs/chromedriver");
			// Create a new instance of the Chrome driver
			driver = new ChromeDriver();
			// To tell the driver that wait for 10 seconds to navigate to link 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// driver navigate to given link
			driver.navigate().to(url);
			Thread.sleep(1000);
		}

		@AfterClass
		public static void tearDown() {
			driver.quit();
		}
	}




