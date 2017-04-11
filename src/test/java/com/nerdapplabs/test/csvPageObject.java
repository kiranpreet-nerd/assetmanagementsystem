package com.nerdapplabs.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ams.pageobject.SignInPageObject;
import com.ams.testsetup.SetUp;
import au.com.bytecode.opencsv.CSVReader;
	 
public class csvPageObject extends SignInPageObject {

	
	public csvPageObject(WebDriver driver) {
		 SetUp.driver = driver;
	}
	
	public csvPageObject() {
		
	}
	csvPageObject csvpageobject = new csvPageObject();
	List<String[]> dataSource = csvpageobject.readCsv("testData.csv");
	
	public void verifyLoginFunctionality() {
	       //for(int i = 1; i <= dataSource.size(); i++)
			String passData = dataSource[1];
			// find element by name and enter invalid email
			WebElement email = driver.findElement(emailTextBox );
			email.sendKeys(passData);
			// find element by name and enter invalid password
			WebElement pwd = driver.findElement(passwordTextBox);
			pwd.sendKeys(passData;
			// click on login button
			driver.findElement(loginBtn).click();
         }
}
			
			
			
			
	}
   /* @Test
	public  verifyLoginWithMultipleTestData() throws InterruptedException{
		//read csv file 
		CSVReader reader = null;
		try
	  	{
			reader = new CSVReader(new FileReader("testData.csv"));
			     String[] cell = reader.readNext();
					
                     while(cell = reader.readNext())!=null){
                    	 String passemail= cell[0];
                    	 String passpwd = cell[1];
				    // find element by name and enter invalid email
						WebElement email = driver.findElement(emailTextBox );
						email.sendKeys(passData);
						// find element by name and enter invalid password
						WebElement pwd = driver.findElement(passwordTextBox);
						pwd.sendKeys(passpwd);
						// click on login button
						driver.findElement(loginBtn).click();
                     }
                     
						
	}catch(IOException e)
		{
		e.printStackTrace();
		}
	
	return 
	}
}*/


	
		
		
		
		
	

	

