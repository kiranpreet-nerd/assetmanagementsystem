package com.nerdapplabs.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginTest {

protected static WebDriver driver;
protected static String result;

@BeforeClass

public static void setup()  {
	
	  System.setProperty("webdriver.chrome.driver","libs/chromedriver");
	  WebDriver driver = new ChromeDriver(); 
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.get("http://localhost:8090/login");
}

@Test
//To check email text box is present
 public void EmailTextBox() {
	
	    WebElement element1 = driver.findElement(By.name("email"));
	   Assert.assertTrue(element1.isDisplayed());
}

@Test
//To check password text box is present
public void PasswordTextBox()

{
	Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
}
@Test
//To check password login button is present
public void LoginButton()
{
	Assert.assertTrue(driver.findElement(By.name("loginbutton")).isDisplayed());
}

 //@AfterClass
 //public void tearDown() {
  // driver.close();	 
   //driver.quit();
//}
}






