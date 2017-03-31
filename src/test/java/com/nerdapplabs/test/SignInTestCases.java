package com.nerdapplabs.test;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.apache.commons.validator.routines.EmailValidator;

/**
* <h1>Automation Testing of signIn page in Asset Management System</h1>
* The SignInTestCases program implements an application that
* simply run the given test methods and give information of passed and failed test cases
* the output on the chrome browser
* <p>
* <b>Note:</b> Giving proper comments in your program makes it more
* user friendly and it is assumed as a high quality code.
*
* @author  Harpreet Kaur
* @version 1.0
* @since   2017-03-31
*/

public class SignInTestCases {
	//class body
	protected static WebDriver driver;
	
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
	public void setup() throws InterruptedException {
		//To set the path of chrome driver
		System.setProperty("webdriver.chrome.driver", "libs/chromedriver");
		// Create a new instance of the Chrome driver
		driver = new ChromeDriver();
		// To tell the driver that wait for 10 seconds to navigate to link
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver navigate to given link
		driver.navigate().to("http://localhost:8092/login");
		Thread.sleep(1000);
	}

	/**
	   * This method is to verify login page title
	   * @param args Unused.
	   * @return Nothing.
	 */
	@Test
	public void verifyLoginPageTitle() {
		//compare expected and actual results if its not matching then written message displayed
		Assert.assertEquals("Please Sign In", driver.findElement(By.className("panel-title")).getText(),"Title not matching");
	}
	/**
	   * This method is to verify email text box is present
	   * @param args Unused.
	   * @return Nothing.
	 */
	@Test
	public void verifyEmailTextBox() {
		Assert.assertTrue(driver.findElement(By.name("email")).isDisplayed());
	}
	/**
	   * This method is to verify password text box is present
	   * @param args Unused.
	   * @return Nothing.
	 */
	@Test
	// To check password text box is present
	public void verifyPasswordTextBox() {
		Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());
	}
	/**
	   * This method is to verify login button is displayed
	   * @param args Unused.
	   * @return Nothing.
	 */
	@Test
	public void verifyLoginButton() {
		Assert.assertTrue(driver.findElement(By.name("loginbutton")).isDisplayed());
	}
	/**
	   * This method is to verify login button is clicked
	   * @param args Unused.
	   * @return Nothing.
	 */
	@Test
	public void verifyLoginClick() {
		// find element by name
		WebElement loginbtn = driver.findElement(By.name("loginbutton"));
		// To check login button is enabled
		Assert.assertTrue(loginbtn.isEnabled());
	}
	/**
	   * This method is to verify when invalid email  enters,user successfully logged in or not
	   * @param args Unused.
	   * @return Nothing.
	 */
	@Test
	public void verifyInvalidEmail() {
		//boolean allowLocal = true;
		// find element by name and enter invalid email
		//WebElement email = driver.findElement(By.name("email"));
		// click on login button
		//driver.findElement(By.name("loginbutton")).click();
		// compare expected and actual results if results not matching then give
		// written message below
		//Assert.assertTrue(EmailValidator.getInstance(allowLocal).isValid(email.sendKeys("!xyz1234hyc%#*%$*")));
	}

	@Test
	/**
	 * This method is to verify when invalid email and password enters,user successfully logged in or not
	 * @param args Unused
	 * @return nothing
	 */
	public void verifyInvalidEmailandInvalidPwd() {
		// find element by name and enter invalid email
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("!xyz1234hyc%#*%$*");
		// find element by name and enter invalid password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("12345#$%^");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results if results not matching then
		// written message shows
		Assert.assertEquals("http://localhost:8092/login", driver.getCurrentUrl(),
				" logged in fails due to invalid email and pasword");
	}

	/**
	 * This method is to verify when valid email and invalid password enters,user successfully logged in or not
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	public void verifyValidEmailandInvalidPwd() {
		// find element by name and enter the valid email
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("harpreet@nerdapplabs.com");
		// find element by name and enter the invalid password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("12345#$%^bn");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results and if it fails then give written message
		Assert.assertEquals("http://localhost:8092/login", driver.getCurrentUrl(), " user should not be logged in");
	}
	
	/**
	 * This method is to verify with registered email and password
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	// To check login functionality
	public void verifyValidEmailandValidPwd() {
		// Enter user email for login
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("harpreet@nerdapplabs.com"); // Enter Password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("preet");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results
	
	}

	/**
	 * This method is to verify forgot password link is present on login page 
	 * @param args Unused
	 * @return nothing
	 * @exception InterruptedException.
	 */
	@Test
	public void verifyForgotPasswordLink() {
		// compare the expected and actual results
		try {
			Thread.sleep(2000);
			Assert.assertEquals("Forgot Password?", driver.findElement(By.linkText("Forgot Password?")).getText(),
					"Please Sign In title not found");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is to verify with email text box empty ,user should fails to login
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	public void verifyEmailEmpty() {
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results
		 Assert.assertEquals( "", driver.findElement(By.name("email")).getText(),"email field is not empty");	
	}

	/**
	 * This method is to verify with password field empty,user should not be able to logged in
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	public void verifyPwdEmpty() {
		
		// click on login button
		driver.findElement(By.name("loginbutton")).click();	
	    Assert.assertEquals( "", driver.findElement(By.name("password")).getText(),"password field is not empty");
	}
	
	@Test
	public void verifyEmailAndPwdEmpty() {
		
		// click on login button
		driver.findElement(By.name("loginbutton")).click();	
		 assertTrue("".equals(driver.findElement(By.name("email")).getText() ));
		 Assert.assertEquals("", driver.findElement(By.name("password")).getText());
	    
	}
	
	
	/**
	 * This method is to verify registered email should not be more than 30 character
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	// Verify email should not be more than 30 character
	public void verifyEmailNotMoreThan30Char() {
		
		 String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
         java.util.regex.Matcher m = p.matcher("");
     
		// Enter user email for login
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("harpreethapreetharpreetharpreet@nerdapplabs.com");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		

		// compare the expected and actual results
		//Assert.assertEquals("
				//"  failed,email should not be more than 30 character");
	}

	/**
	 * This method is to verify email should not be start with special character
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	// Verify email should not start with special character
	public void verifyEmailSpecialChar() {
		// Enter user email for login
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("#preet@nerdapplabs.com");
		// Enter Password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("preet123");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results
		Assert.assertEquals("http://localhost:8092/login", driver.getCurrentUrl(),
				"  failed,email should not be start with specail symbol");
	}

	/**
	 * This method is to verify email should contain combination of symbol,letter and numbers
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	// Verify email should contain combination of symbol,letter and numbers
	public void verifyPwdLetterSymbolNumber() {
		// Enter user email for login
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("preet.kaur@nerdapplabs.com");
		// Enter Password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("#preet2808");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results
		Assert.assertEquals("http://localhost:8092/users", driver.getCurrentUrl(),
				"  failed,password should have  specail symbol,letter,number");
	}

	/**
	 * This method is to verify password should not contain space and period
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	public void verifyPwdSpacePeriod() {
		// Enter user email for login
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("kaurbhullar@nerdapplabs.com");
		// Enter Password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("p reet.2808");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results
		Assert.assertEquals("http://localhost:8092/login", driver.getCurrentUrl(),
				"  failed,password should not have space and period");
	}

	/**
	 * This method is to verify password should have at least one capital letter
	 * @param args Unused
	 * @return nothing
	 */
	@Test
	public void verifyPwdOneCapitalLetter() {
		// Enter user email for login
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("harpreet@nerdapplabs.com");
		// Enter Password
		WebElement pwd = driver.findElement(By.name("password"));
		pwd.sendKeys("Preet@123");
		// click on login button
		driver.findElement(By.name("loginbutton")).click();
		// compare the expected and actual results
		Assert.assertEquals("http://localhost:8092/login", driver.getCurrentUrl(),
				"  failed,password should  have one capital letter");
	}

	// when a test fails, the browser does not close; so moving to tearDown with @After
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
