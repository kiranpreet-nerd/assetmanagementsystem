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
//protected static String result;


@BeforeClass
public static void setup()  {
 System.setProperty("webdriver.chrome.driver","libs/chromedriver");
driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); 
	   
}

@Test
public void VerifyLoginPageTitle() { 
	  driver.navigate().to("http://localhost:8092/login");
	 //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 Assert.assertEquals("Please Sign In", driver.findElement(By.className("panel-title")).getText() ,"Title not matching");
}

@Test
//To check email text box is present
public void VerifyEmailTextBox() {
 driver.navigate().to("http://localhost:8092/login");
//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
Assert.assertTrue(driver.findElement(By.name("email")).isDisplayed());	   
}

@Test
//To check password text box is present
public void VerifyPasswordTextBox() {
 driver.navigate().to("http://localhost:8092/login");
 //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
}

@Test
//To check login button is present
public void VerifyLoginButton() {
 driver.navigate().to("http://localhost:8092/login");
 //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 Assert.assertTrue(driver.findElement(By.name("loginbutton")).isDisplayed());
}

@Test
//To check login button is clicked 
public void VerifyLoginClick() {
driver.navigate().to("http://localhost:8092/login");
// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement wb = driver.findElement(By.name("loginbutton"));
Assert.assertTrue(wb.isEnabled());
}

@Test
//verify invalid email and valid password
public void VerifyInvalidEmailandVaildPwd(){
driver.get("http://localhost:8092/login");
WebElement element3= driver.findElement(By.name("email"));
element3.sendKeys("!xyz1234hyc%#*%$*");
WebElement element4 = driver.findElement(By.name("password"));
element4.sendKeys("preet");
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl()," TestCase Passed");
}

@Test
//verify invalid email and invalid password
public void VerifyInvalidEmailandInvalidPwd(){
driver.get("http://localhost:8092/login");
WebElement element3= driver.findElement(By.name("email"));
element3.sendKeys("!xyz1234hyc%#*%$*");
WebElement element4 = driver.findElement(By.name("password"));
element4.sendKeys("12345#$%^");
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl()," TestCase not Passed");                	    
}

@Test
//verify valid email and invalid password
public void VerifyValidEmailandInvalidPwd(){
driver.get("http://localhost:8092/login");
WebElement element3= driver.findElement(By.name("email"));
element3.sendKeys("harpreet@nerdapplabs.com");
WebElement element4 = driver.findElement(By.name("password"));
element4.sendKeys("12345#$%^bn");
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl()," TestCase Passed");              	    
}

@Test
//To check login functionality
public void VerifyValidEmailandValidPwd() {
 driver.navigate().to("http://localhost:8092/login");
 // Enter user email for login 
 WebElement element7 = driver.findElement(By.name("email"));
 element7.sendKeys("harpreet@nerdapplabs.com");
 //Enter Password
 WebElement element8 = driver.findElement(By.name("password"));
 element8.sendKeys("preet");
 //click on login button
 driver.findElement(By.name("loginbutton")).click(); 
 Assert.assertEquals("http://localhost:8092/users" ,driver.getCurrentUrl()," TestCase Passed"); 
}

@Test
//to check forgot password is present
public void VerifyForgotPasswordLink() {
driver.navigate().to("http://localhost:8092/login");
Assert.assertEquals("Forgot Password?",  driver.findElement(By.linkText("Forgot Password?")).getText());
}

@Test
//Verify email should not be left blank
public void VerifyEmailLeftBlank() {
driver.navigate().to("http://localhost:8092/login");
// Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("preet");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl(),"  failed,Email should not be left blank");
}

@Test
//Verify password should not be left blank
public void VerifyPwdLeftBlank() {
driver.navigate().to("http://localhost:8092/login");
//Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("harpreet@nerdapplabs.com");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl(),"  failed,Please enter the password");
}

@Test
//Verify email should not be more than 30 character
public void VerifyEmail30Char() {
driver.navigate().to("http://localhost:8092/login");
//Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("harpreethapreetharpreetharpreet@nerdapplabs.com");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("preet");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl(),"  failed,email should not be more than 30 character");
}

@Test
//Verify email should not start with special character
public void VerifyEmailSpecialChar() {
driver.navigate().to("http://localhost:8092/login");
//Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("#preet@nerdapplabs.com");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("preet123");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl(),"  failed,email should not be start with specail symbol");
}

@Test
//Verify email should contain combination of symbol,letter and numbers
public void VerifyPwdLetterSymbolNumber() {
driver.navigate().to("http://localhost:8092/login");
//Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("preet.kaur@nerdapplabs.com");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("#preet2808");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/users" ,driver.getCurrentUrl(),"  failed,password should have  specail symbol,letter,number");
}

@Test
//Verify password should not contain space and period
public void VerifyPwdSpacePeriod() {
driver.navigate().to("http://localhost:8092/login");
//Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("kaurbhullar@nerdapplabs.com");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("p reet.2808");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl(),"  failed,password should not have space and period");
}

@Test
//Verify password should have atleast one capital letter
public void VerifyPwdOneCapitalLetter() {
driver.navigate().to("http://localhost:8092/login");
//Enter user email for login 
WebElement element7 = driver.findElement(By.name("email"));
element7.sendKeys("harpreet@nerdapplabs.com");
//Enter Password
WebElement element8 = driver.findElement(By.name("password"));
element8.sendKeys("preet@123");
//click on login button
driver.findElement(By.name("loginbutton")).click(); 
Assert.assertEquals("http://localhost:8092/login" ,driver.getCurrentUrl(),"  failed,password should  have one capital letter");
}

 @AfterClass
 
 public static void tearDown() {
   driver.close();	 
   driver.quit();
}
}






