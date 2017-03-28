package com.nerdapplabs.test;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class TestExampleChromeDriver {

    WebDriver driver;

    @Test
    public void testTitle(){
        /*
        In order to use chrome driver
        Go to: http://chromedriver.storage.googleapis.com/index.html?path=2.9/
        * Download (I'm using mac, hence) chromedriver_mac64.zip
        * Extract to <Project DIR>/libs folder
        * System.setproperty(key,value);
            Key = "webdriver.chrome.driver"
            Value = "chromedriver.{extension}", Absolute path of chromedriver
        */
        System.setProperty("webdriver.chrome.driver","libs/chromedriver");
        // Create a new instance of the Chrome driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
       
        WebDriver driver = new ChromeDriver(); 
        

        // And now use this to visit login page
      
        driver.get("http://localhost:8090/login");
      //we can use it 
        // driver.navigate().to("http://localhost:8090/login")
        
        //go to sign up 
        driver.findElement(By.linkText("SIGN UP")).click();

        //enter email for registration  
        WebElement element1 = driver.findElement(By.name("email"));
       element1.sendKeys("harpreetkaur@nerdapplabs.com");
         
        //enter the firstname 
        WebElement element2 = driver.findElement(By.name("firstname"));
         element2.sendKeys("harpreet");
        
        //enter the lastname
         WebElement element3 = driver.findElement(By.name("lastname"));
        element3.sendKeys("harpreet");
        
        //enter the designation
        WebElement element4 = driver.findElement(By.name("designation"));
        element4.sendKeys("Tester");
        
      //enter the password
         WebElement element5 = driver.findElement(By.name("password"));
         element5.sendKeys("preet123");
        
      //enter the confirm password
        WebElement element6 = driver.findElement(By.name("confirm"));
         element6.sendKeys("preet123");
        
        
        //click on register button
         driver.findElement(By.name("registerbutton")).click(); 
         
          //Click back to login
          driver.findElement(By.linkText("BACK")).click();
        
        
         // Enter user email for login 
         WebElement element7 = driver.findElement(By.name("email"));
        element7.sendKeys("harpreetkaur@nerdapplabs.com");
          
         //wait 20 seconds for  user email to be entered
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       // Thread.sleep(20);
          
         //Enter Password
        WebElement element8 = driver.findElement(By.name("password"));
        element8.sendKeys("preet123");
        
         //wait for 5 seconds for user password to be entered
        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         driver.findElement(By.name("loginbutton")).click(); 
         driver.quit();
         
       
        
         //to back to login page
         //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // driver.navigate().back();
         
           
    }
}
    

    
   
   /* @After
    public void tearDown() {
       driver.quit();
   }
}*/

