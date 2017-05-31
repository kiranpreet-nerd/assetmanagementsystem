package com.ams.testsetup;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ams.test.TestDataReader;

public class Register_Admin extends SetUp {
			
		static List<String[]> dataSource;
	     static String userEmail;
	     static String userFirstName;
		 static String userLastName;
		 static String userDesignation;
		 static String userPassword;
		 static String userConfirmPassword;
		
		 static By adduser = By.linkText("ADD USER");
		 static By useremail = By.name("email");
		 static By firstname = By.name("firstname");
		 static By lastname = By.name("lastname");
		 static By desgnation = By.name("designation");
		 static By password = By.name("password");
		 static By confrmPassword = By.name("confirm");
		 static By addBtn = By.name("registerbutton");
	     static By logout = By.linkText("LOGOUT");
	     static By select_role = By.name("role");
		
	     @Test
		public static  void verify_AddAdmin() {

			// login the application by super_admin
			Login_SuperAdmin.login_SuperAdmin();
			
			try {
				dataSource = TestDataReader.readData("Add_UserBySuperAdmin.csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] userEmailData = dataSource.get(0);
			userEmail = userEmailData[0];

			String[] FirstNameData = dataSource.get(1);
			userFirstName= FirstNameData[0];

			String[] LastNameData = dataSource.get(2);
			userLastName = LastNameData[0];

			String[] DesignationData = dataSource.get(3);
			userDesignation= DesignationData[0];

			String[] passwordData = dataSource.get(4);
			userPassword = passwordData[0];

			String[] confirmPasswordData = dataSource.get(5);
			userConfirmPassword = confirmPasswordData[0];

			// fill the all the fields and click on add button
			driver.findElement(adduser).click();
			driver.findElement(useremail).sendKeys(userEmail);
			driver.findElement(firstname).sendKeys(userFirstName);
			driver.findElement(lastname).sendKeys(userLastName);
			driver.findElement(desgnation).sendKeys(userDesignation);
			driver.findElement(password).sendKeys(userPassword);
			driver.findElement(confrmPassword).sendKeys(userConfirmPassword);
			WebElement assetDrop = driver.findElement(select_role);
			
			// in order to add user as admin,select admin from  "role" drop-down 
			//its not working ,always select the role "employee" need to fixed it
			Select drop = new Select(assetDrop);
			drop.selectByIndex(2);
			driver.findElement(addBtn).click();
		}
	}

