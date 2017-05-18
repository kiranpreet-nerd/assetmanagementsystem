package com.ams.testsetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Click_ConfirmationLink  {
	
	WebDriver driver;
	
	public static By table = By.cssSelector("table[id = 'messagelist']");
	public static By rows = By.tagName("tr");
	public static By cols = By.tagName("td");
	By link = By.linkText("Registration Confirmation");
	ArrayList<String> actualList = new ArrayList<String>();

	@Test
	public void Find_Link() {
		
			try {
				Open_EmailForConfirmation.Open_Mail(driver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		WebElement webTable = driver.findElement(table);
		List<WebElement> tableRow = webTable.findElements(rows);
		for (int rnum = 1; rnum < tableRow.size(); rnum++) {
			List<WebElement> tableColns = tableRow.get(rnum).findElements(cols);
			for (int rcol = 0; rcol < tableColns.size(); rcol++) {
				String values = tableColns.get(rcol).getText();
				actualList.add(values);
				//if(tableColns.get(rcol).getText().equals(link)) {	
				//Actions action = new Actions(driver);
				//action.doubleClick((WebElement) link).perform();
				}
			}
		}
	}


		

