package com.nerdapplabs.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.internal.Nullable;

public class SignInTestCases {

protected static WebDriver driver;
protected static String result;


@BeforeClass

public static void setup()  {
	    System.setProperty("webdriver.chrome.driver","libs/chromedriver");
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);   
}

@Test
public void VerifyLoginPageTitle() {
	   
	  //driver = new ChromeDriver();
	  driver.navigate().to("http://localhost:8090/login");
	 //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 Assert.assertEquals("Please Sign In", driver.findElement(By.className("panel-title")).getText() ,"Title not matching");
}


@Test
//To check email text box is present
 public void VerifyEmailTextBox() {
	    
	    //driver = new ChromeDriver();
	    driver.navigate().to("http://localhost:8090/login");
	   //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    WebElement element1 = driver.findElement(By.name("email"));
	    Assert.assertTrue(element1.isDisplayed());	   
}

@Test
//To check password text box is present
public void VerifyPasswordTextBox() {
	   
	   //driver = new ChromeDriver();
       driver.navigate().to("http://localhost:8090/login");
       //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
}

@Test
//To check login button is present
public void VerifyLoginButton() {
	
	   //driver= new  ChromeDriver();
       driver.navigate().to("http://localhost:8090/login");
       //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   Assert.assertTrue(driver.findElement(By.name("loginbutton")).isDisplayed());
}

@Test
//To check login button is clicked 
public void VerifyLoginClick() {
	
       driver.navigate().to("http://localhost:8090/login");
      // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       WebElement wb = driver.findElement(By.name("loginbutton"));
	   Assert.assertTrue(wb.isEnabled());
}

@Test
//To check login functionality
      public void VerifyLoginFunctionality() {
	
        driver.navigate().to("http://localhost:8090/login");
        // Enter user email for login 
	    WebElement element7 = driver.findElement(By.name("email"));
	    element7.sendKeys("harpreet@nerdapplabs.com");
	    //Enter Password
        WebElement element8 = driver.findElement(By.name("password"));
        element8.sendKeys("preet");
        //click on login button
        driver.findElement(By.name("loginbutton")).click();
       // assertTrue(driver.getCurrentUrl().endsWith("passed"));   
}


 //@AfterClass
 
 //public void tearDown() {
  // driver.close();	 
   //driver.quit();
//}
}






